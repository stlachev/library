package com.bookstore.library.entity.dto;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrdersWithCustomerDTO extends OrdersDTO {

    @JsonIgnore
    private CustomerNoOrdersDTO customer;

    private List<OrdersListDTO> orders = new ArrayList<OrdersListDTO>(); //


    public CustomerNoOrdersDTO getOrderCustomer() {
        return this.customer;
    }

    public void setOrderCustomer(CustomerNoOrdersDTO customer) {
        this.customer = customer;
    }

//-----
    public List<OrdersListDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersListDTO> orders) {
        this.orders =  orders;
    }

/*
    protected List<OrdersListDTO> orders = new ArrayList<OrdersListDTO>();

    public List<OrdersListDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersListDTO> orders) {
        this.orders = orders;
    }
*/
}