package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.OrdersDTO;

import jakarta.validation.constraints.NotNull;

public interface OrdersService {

    public List<OrdersDTO> findAll();
    public Optional<OrdersDTO> get(@NotNull Long id);
    public OrdersDTO create(@NotNull OrdersDTO orderDTO);
    public OrdersDTO update(@NotNull OrdersDTO ordersDTO);
    public Optional<OrdersDTO> delete(@NotNull Long id);

    public OrdersDTO updateCustomer(@NotNull Long id, @NotNull OrdersDTO orderDTO);
    public OrdersDTO addBook(@NotNull Long id, @NotNull OrdersDTO orderDTO);
}
