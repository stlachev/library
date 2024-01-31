package com.bookstore.library.entity.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrdersWithCustomerDTO extends OrdersDTO {

    @JsonIgnore
    private CustomerNoOrdersDTO customer;

    public CustomerNoOrdersDTO getOrderCustomer() {
        return this.customer;
    }

    public void setOrderCustomer(CustomerNoOrdersDTO customer) {
        this.customer = customer;
    }

/*
    protected Set<OrdersListDTO> orders = new HashSet<OrdersListDTO>();

    public Set<OrdersListDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<OrdersListDTO> orders) {
        this.orders = orders;
    }
*/
}