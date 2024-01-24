package com.bookstore.library.entity.dto;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrdersWithCustomerDTO extends OrdersDTO {

//    @JsonIgnore
/*    private CustomerNoOrdersDTO customer;

    public CustomerNoOrdersDTO getCustomerNoOrders() {
        return this.customer;
    }

    public void setCustomerNoOrders(CustomerNoOrdersDTO customer) {
        this.customer = customer;
    }
*/
    private CustomerDTO customer;
    @JsonIgnore
    private Set<OrdersListDTO> orders = new HashSet<>();
    public CustomerDTO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}