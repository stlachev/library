package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.dto.OrdersDTO;
import com.bookstore.library.service.OrdersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrdersDTO>> getAll() throws Exception {
        List<OrdersDTO> ordersDTOList = ordersService.findAll();
        return !ordersDTOList.isEmpty() ?
                new ResponseEntity<>(ordersDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrdersDTO> getById(@PathVariable Long id) throws Exception {
        Optional<OrdersDTO> order = ordersService.getById(id);
        return order.map(orderDTO -> new ResponseEntity<>(orderDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<OrdersDTO> createOrderResponseEntity(@Valid @RequestBody OrdersDTO ordersDTO) throws ServerException {
        OrdersDTO createdOrdersDTO = ordersService.createOrder(ordersDTO);
        return new ResponseEntity<>(createdOrdersDTO, HttpStatus.CREATED);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<OrdersDTO> deleteCustomerById(@PathVariable Long id) throws Exception {
        Optional<OrdersDTO> order = ordersService.deleteOrderById(id);
        return order.map(ordersDTO -> new ResponseEntity<>(ordersDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<OrdersDTO> update(@Valid @RequestBody OrdersDTO ordersDTO) throws Exception {
        OrdersDTO updatedOrdersDTO = ordersService.update(ordersDTO);
        return (updatedOrdersDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedOrdersDTO, HttpStatus.OK);
    }

}
