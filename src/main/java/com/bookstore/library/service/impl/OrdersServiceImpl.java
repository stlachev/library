package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Orders;
import com.bookstore.library.entity.dto.OrdersDTO;
import com.bookstore.library.repository.OrdersRepository;
import com.bookstore.library.service.BookService;
import com.bookstore.library.service.CustomerService;
import com.bookstore.library.service.OrdersService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
            CustomerService customerService,
            BookService bookService,
            ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrdersDTO> findAll() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(orderP -> modelMapper.map(orderP, OrdersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrdersDTO> getById(@NotNull Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        return (order.isPresent()) ?
            order.map(orderOp -> modelMapper.map(order, OrdersDTO.class)) :
            Optional.empty();
    }

    @Override
    public OrdersDTO createOrder(@NotNull OrdersDTO orderDTO) {

        Orders order = modelMapper.map(orderDTO, Orders.class);
/*
        Optional<CustomerDTO> cDto = customerService.getById(3L);
        if (cDto.isPresent()) {
            Customer c = modelMapper.map(cDto, Customer.class);
            order.setCustomer(c);
        }
*/
        Orders orderNew = ordersRepository.save(order);
        return modelMapper.map(orderNew, OrdersDTO.class);
    }

    @Override
    public Optional<OrdersDTO> deleteOrderById(@NotNull Long id) {
        Optional<Orders> order =  ordersRepository.findById(id);
        if (!order.isPresent()) {
            return Optional.empty();
        }
        ordersRepository.deleteById(id);
        return order.map(orderOp -> modelMapper.map(order, OrdersDTO.class));
    }

    @Override
    public OrdersDTO update(@NotNull OrdersDTO ordersDTO) {
        Orders orders = ordersRepository.findById(ordersDTO.getId()).orElse(null);
        if (orders == null) {
            return null;
        }
        modelMapper.map(ordersDTO, orders);
        ordersRepository.save(orders);
        return modelMapper.map(orders, OrdersDTO.class);
    }

}
