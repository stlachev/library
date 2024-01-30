package com.bookstore.library.entity.dto;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerNoOrdersDTO extends CustomerDTO {

    @JsonIgnore
    private Collection<CustomerDTO> orders = new HashSet<CustomerDTO>();

}
