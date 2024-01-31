package com.bookstore.library.entity.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class OrdersDTO {
    private Long id;

    @JsonIgnore
    private CustomerDTO customer;

    private Set<OrdersListDTO> orders = new HashSet<OrdersListDTO>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Set<OrdersListDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<OrdersListDTO> orders) {
        this.orders =  orders;
    }

    public void addOrders(OrdersListDTO orders) {
        this.orders.add(orders);
        orders.setOrder(this);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customer='" + getCustomer() + "'" +
            "}";
    }

}
