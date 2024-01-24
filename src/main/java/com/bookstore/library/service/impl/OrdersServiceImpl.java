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
import com.bookstore.library.entity.OrdersList;
import com.bookstore.library.entity.dto.OrdersDTO;
import com.bookstore.library.entity.dto.OrdersWithCustomerDTO;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.repository.CustomerRepository;
import com.bookstore.library.repository.OrdersListRepository;
import com.bookstore.library.repository.OrdersRepository;
import com.bookstore.library.service.OrdersService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersRepository ordersRepository;
    private final OrdersListRepository ordersListRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
            OrdersListRepository ordersListRepository,
            CustomerRepository customerRepository,
            BookRepository bookRepository,
            ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.ordersListRepository = ordersListRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrdersWithCustomerDTO> findAll() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(orderP -> modelMapper.map(orderP, OrdersWithCustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrdersWithCustomerDTO> get(@NotNull Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        return (order.isPresent()) ?
            order.map(orderOp -> modelMapper.map(order, OrdersWithCustomerDTO.class)) :
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
        Orders order = modelMapper.map(orderDTO, Orders.class);
        order = ordersRepository.save(order);
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
        OrdersList ordersList = ordersListRepository.findByOrderId(orderDTO.getId());
        if (ordersList == null) {
            ordersList= new OrdersList();
        }
        ordersList.setBook(book.get());
        ordersList.setOrder(order);
        ordersList = ordersListRepository.save(ordersList);
        order.addOrdersList(ordersList);
        Orders orderNew = ordersRepository.save(order);
        return modelMapper.map(orderNew, OrdersDTO.class);
    }
}
