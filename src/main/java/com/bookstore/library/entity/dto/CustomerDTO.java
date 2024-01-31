package com.bookstore.library.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CustomerDTO {
    private Long id;
    private String customer_name;
    private String customer_address;

    private List<OrdersDTO> orders = new ArrayList<OrdersDTO>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return this.customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return this.customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public List<OrdersDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customer_name='" + getCustomer_name() + "'" +
            ", customer_address='" + getCustomer_address() + "'" +
            "}";
    }

}
