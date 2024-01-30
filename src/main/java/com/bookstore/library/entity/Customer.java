package com.bookstore.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Table(name = "customers")
@Entity
public class Customer implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer_name;
    private String customer_address;

    @BatchSize(size=10)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Orders> orders = new HashSet<Orders>();

    public void addOrder(Orders order) {
        this.orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Orders order) {
        order.setOrders(null);
        this.orders.remove(order);
    }

    public void removeOrder() {
        Iterator<Orders> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            Orders orders = iterator.next();
            orders.setOrders(null);
            iterator.remove();
        }
    }

    @Transactional(readOnly= true)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transactional(readOnly= true)
    public String getCustomer_name() {
        return this.customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    @Transactional(readOnly= true)
    public String getCustomer_address() {
        return this.customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    @Transactional(readOnly= true)
    public Set<Orders> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
/*
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customer_name='" + getCustomer_name() + "'" +
            ", customer_address='" + getCustomer_address() + "'" +
            ", orders='" + getOrders() + "'" +
            "}";
    }
*/
}