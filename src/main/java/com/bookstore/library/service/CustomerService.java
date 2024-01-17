package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.CustomerDTO;

import jakarta.validation.constraints.NotNull;

public interface CustomerService {

    public List<CustomerDTO> findAll();
    public Optional<CustomerDTO> getById(@NotNull Long id);
    public CustomerDTO createCustomer(@NotNull CustomerDTO customerDTO);
    public Optional<CustomerDTO> deleteCustomerById(@NotNull Long id);
    public CustomerDTO update(@NotNull CustomerDTO customerDTO);
    public CustomerDTO findByName(@NotNull String name);
}
