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

}