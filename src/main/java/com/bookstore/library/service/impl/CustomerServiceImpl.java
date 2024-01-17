package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Customer;
import com.bookstore.library.entity.dto.CustomerDTO;
import com.bookstore.library.repository.CustomerRepository;
import com.bookstore.library.service.CustomerService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getById(@NotNull Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return (customer.isPresent()) ?
            customer.map(customerOp -> modelMapper.map(customer, CustomerDTO.class)) :
            Optional.empty();
    }

    @Override
    public CustomerDTO createCustomer(@NotNull CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer customerNew = customerRepository.save(customer);
        return modelMapper.map(customerNew, CustomerDTO.class);
    }

    @Override
    public Optional<CustomerDTO> deleteCustomerById(@NotNull Long id) {
        Optional<Customer> customer =  customerRepository.findById(id);
        if (!customer.isPresent()) {
            return Optional.empty();
        }
        customerRepository.deleteById(id);
        return customer.map(authorOp -> modelMapper.map(customer, CustomerDTO.class));
    }

    @Override
    public CustomerDTO update(@NotNull CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerDTO.getId()).orElse(null);
        if (customer == null) {
            return null;
        }
        modelMapper.map(customerDTO, customer);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findByName(@NotNull String name) {
        Customer customer = customerRepository.findByName(name);
        if (customer != null) {
            return modelMapper.map(customer, CustomerDTO.class);
        }
        return null;
    }

}
