package com.bookstore.library.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerNoOrdersDTO extends CustomerDTO {

    @JsonIgnore
    private List<CustomerDTO> orders = new ArrayList<CustomerDTO>();

}
