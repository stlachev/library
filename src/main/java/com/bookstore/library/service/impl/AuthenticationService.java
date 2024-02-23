package com.bookstore.library.service.impl;


import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.library.auth.Role;
import com.bookstore.library.config.jwt.JwtService;
import com.bookstore.library.entity.Customer;
import com.bookstore.library.entity.auth.AuthenticationRequest;
import com.bookstore.library.entity.auth.AuthenticationResponse;
import com.bookstore.library.entity.auth.RegisterRequest;
import com.bookstore.library.entity.auth.Token;
import com.bookstore.library.entity.auth.TokenType;
import com.bookstore.library.repository.CustomerRepository;
import com.bookstore.library.repository.TokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(CustomerRepository customerRepository, TokenRepository tokenRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;

    }

    public AuthenticationResponse register(RegisterRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(Role.USER);
        var savedCustomer = customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(customer);
        var refreshToken = jwtService.generateRefreshToken(customer);
        saveCustomerToken(savedCustomer, jwtToken);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse adminRegister(RegisterRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(request.getRole());
        var savedCustomer = customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(customer);
        var refreshToken = jwtService.generateRefreshToken(customer);
        saveCustomerToken(savedCustomer, jwtToken);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var customer = customerRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(customer);
        var refreshToken = jwtService.generateRefreshToken(customer);

        revokeAllCustomerTokens(customer);
        saveCustomerToken(customer, jwtToken);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    private void saveCustomerToken(Customer savedCustomer, String jwtToken) {
        Token token = new Token();
        token.setCustomer(savedCustomer);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    private void revokeAllCustomerTokens(Customer customer) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByCustomer(customer.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        final String refreshToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            Customer customer = this.customerRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, customer)) {
                var accessToken = jwtService.generateToken(customer);
                revokeAllCustomerTokens(customer);
                saveCustomerToken(customer, accessToken);

                new ObjectMapper().writeValue(response.getOutputStream(), new AuthenticationResponse(accessToken, refreshToken));
            }
        }
    }
}
