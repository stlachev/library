package com.bookstore.library.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.auth.AuthenticationRequest;
import com.bookstore.library.entity.auth.AuthenticationResponse;
import com.bookstore.library.entity.auth.RegisterRequest;
import com.bookstore.library.service.impl.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {
        private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        if (authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
//            return ResponseEntity.badRequest().build();
//            return ResponseEntity.ofNullable(null);
        }
        return ResponseEntity.ok(authenticationResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adminregister")
    public ResponseEntity<AuthenticationResponse> adminRegister(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        if (authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
//            return ResponseEntity.badRequest().build();
//            return ResponseEntity.ofNullable(null);
        }
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(@Valid @RequestBody HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
