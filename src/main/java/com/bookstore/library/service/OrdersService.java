package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.OrdersDTO;

import jakarta.validation.constraints.NotNull;

public interface OrdersService {

    public List<OrdersDTO> findAll();
    public Optional<OrdersDTO> getById(@NotNull Long id);
    public OrdersDTO createOrder(@NotNull OrdersDTO orderDTO);
    public Optional<OrdersDTO> deleteOrderById(@NotNull Long id);
    public OrdersDTO update(@NotNull OrdersDTO ordersDTO);
}
