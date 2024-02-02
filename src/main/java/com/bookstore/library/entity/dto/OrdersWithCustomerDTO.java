package com.bookstore.library.entity.dto;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrdersWithCustomerDTO extends OrdersDTO {

    @JsonIgnore
    private CustomerNoOrdersDTO customer;

    private List<OrdersListDTO> orders = new ArrayList<OrdersListDTO>();
//    private List<OrdersListAuthorDTO> orders = new ArrayList<OrdersListAuthorDTO>();

    public CustomerNoOrdersDTO getOrderCustomer() {
        return this.customer;
    }

    public void setOrderCustomer(CustomerNoOrdersDTO customer) {
        this.customer = customer;
    }

    public List<OrdersListDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersListDTO> orders) {
        this.orders =  orders;
    }

/*
    public List<OrdersListAuthorDTO> getOrdersListAuthorDTO() {
        return this.orders;
    }

    public void setOrdersListAuthor(List<OrdersListAuthorDTO> orders) {
        this.orders =  orders;
    }

//-----
    public void addOrdersListAuthor(OrdersListAuthorDTO orders) {
        this.orders.add(orders);
        orders.setOrder(this);
    }

    public void removeOrdersListAuthor(OrdersListAuthorDTO orders) {
        orders.setOrder(null);
        this.orders.remove(orders);
    }

    public void removeOrdersListAuthor() {
        Iterator<OrdersListAuthorDTO> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            OrdersListAuthorDTO orders = iterator.next();
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
*/
}