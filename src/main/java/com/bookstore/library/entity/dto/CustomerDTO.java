package com.bookstore.library.entity.dto;

import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CustomerDTO {
    protected Long id;
    protected String customer_name;
    protected String customer_address;

    protected Set<OrdersDTO> orders = new HashSet<OrdersDTO>();

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

    public Set<OrdersDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<OrdersDTO> orders) {
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
