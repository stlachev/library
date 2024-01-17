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

import com.bookstore.library.entity.dto.CustomerDTO;
import com.bookstore.library.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAll() throws Exception {
        List<CustomerDTO> customerDTOList = customerService.findAll();
        return !customerDTOList.isEmpty() ?
                new ResponseEntity<>(customerDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) throws Exception {
        Optional<CustomerDTO> customer = customerService.getById(id);
        return customer.map(customerDTO -> new ResponseEntity<>(customerDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws ServerException {
        CustomerDTO createdCustomerDTO = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable Long id) throws Exception {
        Optional<CustomerDTO> customer = customerService.deleteCustomerById(id);
        return customer.map(customerDTO -> new ResponseEntity<>(customerDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
        CustomerDTO updatedCustomerDTO = customerService.update(customerDTO);
        return (updatedCustomerDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
    }

    @GetMapping(path="/name/{name}")
    public ResponseEntity<CustomerDTO> findByName(@PathVariable String name)  throws Exception {
        CustomerDTO customerDTO = customerService.findByName(name);
        return (customerDTO == null) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

}
