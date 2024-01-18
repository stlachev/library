package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.Customer;
import com.bookstore.library.entity.Orders;
import com.bookstore.library.entity.dto.OrdersDTO;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.repository.CustomerRepository;
import com.bookstore.library.repository.OrdersRepository;
import com.bookstore.library.service.OrdersService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
            CustomerRepository customerRepository,
            BookRepository bookRepository,
            ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
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
    public Optional<OrdersDTO> get(@NotNull Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        return (order.isPresent()) ?
            order.map(orderOp -> modelMapper.map(order, OrdersDTO.class)) :
            Optional.empty();
    }

    @Override
    public OrdersDTO create(@NotNull OrdersDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        Orders orderNew = ordersRepository.save(order);
        return modelMapper.map(orderNew, OrdersDTO.class);
    }

    @Override
    public OrdersDTO update(@NotNull OrdersDTO orderDTO) {
    //    Orders orders = ordersRepository.findById(ordersDTO.getId()).orElse(null);
    //    if (orders == null) {
    //        return null;
    //    }
    //    modelMapper.map(ordersDTO, orders);
        Orders order = modelMapper.map(orderDTO, Orders.class);
        ordersRepository.save(order);
        return modelMapper.map(order, OrdersDTO.class);
    }

    @Override
    public Optional<OrdersDTO> delete(@NotNull Long id) {
        Optional<Orders> order =  ordersRepository.findById(id);
        if (!order.isPresent()) {
            return Optional.empty();
        }
        ordersRepository.deleteById(id);
        return order.map(orderOp -> modelMapper.map(order, OrdersDTO.class));
    }

//--------------------------------------------------

    @Override
    public OrdersDTO updateCustomer(@NotNull Long id, @NotNull OrdersDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return null;
        }
        order.setCustomer(customer.get());
        Orders orderNew = ordersRepository.save(order);
        return modelMapper.map(orderNew, OrdersDTO.class);
    }

    @Override
    public OrdersDTO addBook(@NotNull Long id, @NotNull OrdersDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            return null;
        }
        order.addBooks(book.get());
        Orders orderNew = ordersRepository.save(order);
        return modelMapper.map(orderNew, OrdersDTO.class);
    }

}
