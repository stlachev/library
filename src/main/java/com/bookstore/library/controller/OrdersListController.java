package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.util.Collection;
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

import com.bookstore.library.entity.dto.OrdersListDTO;
import com.bookstore.library.service.OrdersListService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderslist")
public class OrdersListController {

    @Autowired
    private final OrdersListService ordersListService;

    public OrdersListController(OrdersListService ordersListService) {
        this.ordersListService = ordersListService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrdersListDTO>> getAll() throws Exception {
        List<OrdersListDTO> ordersListDTO = ordersListService.findAll();
        return ordersListDTO.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(ordersListDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersListDTO> get(@PathVariable Long id) throws Exception {
        Optional<OrdersListDTO> order = ordersListService.get(id);
        return order.map(orderOp -> new ResponseEntity<>(orderOp, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<OrdersListDTO> create(@Valid @RequestBody OrdersListDTO ordersListDTO) throws ServerException {
        OrdersListDTO createdListOrdersDTO = ordersListService.create(ordersListDTO);
        return new ResponseEntity<>(createdListOrdersDTO, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<OrdersListDTO> update(@Valid @RequestBody OrdersListDTO ordersListDTO) throws Exception {
        OrdersListDTO updatedOrdersListDTO = ordersListService.update(ordersListDTO);
        return (updatedOrdersListDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedOrdersListDTO, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<OrdersListDTO> delete(@PathVariable Long id) throws Exception {
        Optional<OrdersListDTO> order = ordersListService.delete(id);
        return order.map(orderOp -> new ResponseEntity<>(orderOp, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

//-----------------------------------
    @GetMapping("/order/{id}")
    public ResponseEntity<Collection<OrdersListDTO>> findByOrderId(@PathVariable Long id) throws Exception {
        Collection<OrdersListDTO> ordersListDTO = ordersListService.findByOrderId(id);
        return ordersListDTO.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(ordersListDTO, HttpStatus.OK);
    }

}
