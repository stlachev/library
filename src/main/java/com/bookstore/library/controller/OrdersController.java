package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("")
    public ResponseEntity<List<OrdersDTO>> getAll() throws Exception {
        List<OrdersDTO> ordersDTOList = ordersService.findAll();
        return ordersDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(ordersDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> get(@PathVariable Long id) throws Exception {
        Optional<OrdersDTO> order = ordersService.get(id);
        return order.map(orderDTO -> new ResponseEntity<>(orderDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<OrdersDTO> create(@Valid @RequestBody OrdersDTO ordersDTO) throws ServerException {
        OrdersDTO createdOrdersDTO = ordersService.create(ordersDTO);
        return new ResponseEntity<>(createdOrdersDTO, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<OrdersDTO> update(@Valid @RequestBody OrdersDTO ordersDTO) throws Exception {
        OrdersDTO updatedOrdersDTO = ordersService.update(ordersDTO);
        return (updatedOrdersDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedOrdersDTO, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<OrdersDTO> delete(@PathVariable Long id) throws Exception {
        Optional<OrdersDTO> order = ordersService.delete(id);
        return order.map(ordersDTO -> new ResponseEntity<>(ordersDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

//----------------------

    @PutMapping("/customer/{id}")
    public ResponseEntity<OrdersDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody OrdersDTO orderDTO) throws Exception {
        OrdersDTO updatedOrdersDTO = ordersService.updateCustomer(id, orderDTO);
        return (updatedOrdersDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedOrdersDTO, HttpStatus.OK);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<OrdersDTO> addBook(@PathVariable Long id, @Valid @RequestBody OrdersDTO orderDTO) throws Exception {
        OrdersDTO updatedOrdersDTO = ordersService.addBook(id, orderDTO);
        return (updatedOrdersDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedOrdersDTO, HttpStatus.OK);
    }

}
