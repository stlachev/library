package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.auth.ChangePasswordRequest;
import com.bookstore.library.entity.dto.CustomerDTO;
import com.bookstore.library.service.CustomerService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@Hidden
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getAll() throws Exception {
        List<CustomerDTO> customerDTOList = customerService.findAll();
        return customerDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> get(@PathVariable Long id) throws Exception {
        Optional<CustomerDTO> customer = customerService.get(id);
        return customer.map(customerOp -> new ResponseEntity<>(customerOp, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customerDTO) throws ServerException {
        CustomerDTO createdCustomerDTO = customerService.create(customerDTO);
        return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
        CustomerDTO updatedCustomerDTO = customerService.update(customerDTO);
        return (updatedCustomerDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> delete(@PathVariable Long id) throws Exception {
        Optional<CustomerDTO> customer = customerService.delete(id);
        return customer.map(customerOp -> new ResponseEntity<>(customerOp, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

//-------------------------------------------

    @GetMapping("/find/{name}")
    public ResponseEntity<List<CustomerDTO>> findByName(@PathVariable String name)  throws Exception {
        List<CustomerDTO> customerDTOList = customerService.findByName(name);
        return customerDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        customerService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
