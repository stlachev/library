package com.bookstore.library.entity.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String address;
    private LocalDateTime created;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    private List<OrdersDTO> orders = new ArrayList<OrdersDTO>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
            ", customer_name='" + getName() + "'" +
            ", customer_address='" + getAddress() + "'" +
            "}";
    }

}
