package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.OrdersListDTO;

import jakarta.validation.constraints.NotNull;

public interface OrdersListService {
    public List<OrdersListDTO> findAll();
    public Optional<OrdersListDTO> get(@NotNull Long id);
    public OrdersListDTO create(@NotNull OrdersListDTO orderListDTO);
    public OrdersListDTO update(@NotNull OrdersListDTO ordersListDTO);
    public Optional<OrdersListDTO> delete(@NotNull Long id);

    public List<OrdersListDTO> findByOrderId(@NotNull Long id);
}
