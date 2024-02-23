package com.bookstore.library.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Customer;
import com.bookstore.library.entity.Orders;
import com.bookstore.library.entity.auth.ChangePasswordRequest;
import com.bookstore.library.entity.dto.CustomerDTO;
import com.bookstore.library.repository.CustomerRepository;
import com.bookstore.library.repository.OrdersRepository;
import com.bookstore.library.service.CustomerService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository,
            OrdersRepository ordersRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly= true)
    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly= true)
    @Override
    public Optional<CustomerDTO> get(@NotNull Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return (customer.isPresent()) ?
            customer.map(customerOp -> modelMapper.map(customer, CustomerDTO.class)) :
            Optional.empty();
    }

    @Override
    public CustomerDTO create(@NotNull CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer customerNew = customerRepository.save(customer);
        return modelMapper.map(customerNew, CustomerDTO.class);
    }

    @Override
    public CustomerDTO update(@NotNull CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        List<Orders> customerOrders = ordersRepository.findOrdersByCustomerId(customerDTO.getId());
        customer.setOrders(customerOrders);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public Optional<CustomerDTO> delete(@NotNull Long id) {
        Optional<Customer> customer =  customerRepository.findById(id);
        if (!customer.isPresent()) {
            return Optional.empty();
        }
        customerRepository.deleteById(id);
        return customer.map(authorOp -> modelMapper.map(customer, CustomerDTO.class));
    }

//------------------------

    @Transactional(readOnly= true)
    @Override
    public List<CustomerDTO> findByName(@NotNull String name) {
        List<Customer> customers = customerRepository.findByName(name);
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedCustomer) {
        var customer = (Customer) ((UsernamePasswordAuthenticationToken) connectedCustomer).getPrincipal();
        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), customer.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        // update the password
        customer.setPassword(passwordEncoder.encode(request.getNewPassword()));
        // save the new password
        customerRepository.save(customer);
    }

}
