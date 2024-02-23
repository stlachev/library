package com.bookstore.library.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.auth.ChangePasswordRequest;
import com.bookstore.library.entity.dto.CustomerDTO;

import jakarta.validation.constraints.NotNull;

public interface CustomerService {

    public List<CustomerDTO> findAll();
    public Optional<CustomerDTO> get(@NotNull Long id);
    public CustomerDTO create(@NotNull CustomerDTO customerDTO);
    public CustomerDTO update(@NotNull CustomerDTO customerDTO);
    public Optional<CustomerDTO> delete(@NotNull Long id);
    
    public List<CustomerDTO> findByName(@NotNull String name);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
