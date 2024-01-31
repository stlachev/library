package com.bookstore.library.entity.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class OrdersDTO {
    private Long id;

    @JsonIgnore
    private CustomerDTO customer;

    private List<OrdersListDTO> orders = new ArrayList<OrdersListDTO>();

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

    public List<OrdersListDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersListDTO> orders) {
        this.orders =  orders;
    }

//-----------

    public void addOrders(OrdersListDTO orders) {
        this.orders.add(orders);
        orders.setOrder(this);
    }

    public void removeOrders(OrdersListDTO orders) {
        orders.setOrder(null);
        this.orders.remove(orders);
    }

    public void removeOrdersLists() {
        Iterator<OrdersListDTO> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            OrdersListDTO orders = iterator.next();
            orders.setOrder(null);
            iterator.remove();
        }
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customer='" + getCustomer() + "'" +
            "}";
    }

}
