package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.OrdersList;
import com.bookstore.library.entity.dto.OrdersListDTO;
import com.bookstore.library.repository.OrdersListRepository;
import com.bookstore.library.service.OrdersListService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class OrdersListServiceImpl implements OrdersListService{

    @Autowired
    private final OrdersListRepository ordersListRepository;
    private final ModelMapper modelMapper;

    public OrdersListServiceImpl(OrdersListRepository ordersListRepository,
            ModelMapper modelMapper) {
        this.ordersListRepository = ordersListRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly= true)
    @Override
    public List<OrdersListDTO> findAll() {
        List<OrdersList> ordersList = ordersListRepository.findAll();
        return ordersList.stream()
                .map(orderOp -> modelMapper.map(orderOp, OrdersListDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly= true)
    @Override
    public Optional<OrdersListDTO> get(@NotNull Long id) {
        Optional<OrdersList> ordersList = ordersListRepository.findById(id);
        return (ordersList.isPresent()) ?
            ordersList.map(orderOp -> modelMapper.map(ordersList, OrdersListDTO.class)) :
            Optional.empty();
    }

    @Override
    public OrdersListDTO create(@NotNull OrdersListDTO ordersListDTO) {
        OrdersList ordersList = modelMapper.map(ordersListDTO, OrdersList.class);
        ordersList = ordersListRepository.save(ordersList);
        return modelMapper.map(ordersList, OrdersListDTO.class);
    }

    @Override
    public OrdersListDTO update(@NotNull OrdersListDTO ordersListDTO) {
        OrdersList ordersList = modelMapper.map(ordersListDTO, OrdersList.class);
        ordersList = ordersListRepository.save(ordersList);
        return modelMapper.map(ordersList, OrdersListDTO.class);
    }

    @Override
    public Optional<OrdersListDTO> delete(@NotNull Long id) {
        Optional<OrdersList> ordersList =  ordersListRepository.findById(id);
        if (!ordersList.isPresent()) {
            return Optional.empty();
        }
        ordersListRepository.deleteById(id);
        return ordersList.map(orderOp -> modelMapper.map(ordersList, OrdersListDTO.class));
    }

//------------------------------------

    @Transactional(readOnly= true)
    @Override
    public List<OrdersListDTO> findByOrderId(@NotNull Long id) {
        List<OrdersList> ordersList = ordersListRepository.findByOrderId(id);

        return ordersList.stream()
                .map(orderOp -> modelMapper.map(orderOp, OrdersListDTO.class))
                .collect(Collectors.toList());
    }
/*
        List<OrdersListDTO> orders = new ArrayList<OrdersListDTO>();
        if (ordersList == null || ordersList.isEmpty()) {
            return orders;
        }
        for (OrdersList _order : ordersList) {
            orders.add(modelMapper.map(_order, OrdersListDTO.class));
        }
        return orders;

    }
*/
}
