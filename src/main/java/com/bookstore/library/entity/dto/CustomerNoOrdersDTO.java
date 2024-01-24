package com.bookstore.library.entity.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerNoOrdersDTO extends CustomerDTO {

    @JsonIgnore
    private Set<OrdersWithCustomerDTO> orders = new HashSet<>();

}
