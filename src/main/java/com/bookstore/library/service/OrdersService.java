package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.OrdersDTO;
import com.bookstore.library.entity.dto.OrdersWithCustomerDTO;

import jakarta.validation.constraints.NotNull;

public interface OrdersService {
    
    public List<OrdersWithCustomerDTO> findAll();
    public Optional<OrdersWithCustomerDTO> get(@NotNull Long id);
    public OrdersWithCustomerDTO create(@NotNull OrdersDTO orderDTO);
    public OrdersWithCustomerDTO update(@NotNull OrdersDTO ordersDTO);
    public Optional<OrdersWithCustomerDTO> delete(@NotNull Long id);

    public OrdersWithCustomerDTO updateCustomer(@NotNull Long id, @NotNull OrdersDTO orderDTO);
    public OrdersWithCustomerDTO addBook(@NotNull Long id, @NotNull OrdersDTO orderDTO);
}
