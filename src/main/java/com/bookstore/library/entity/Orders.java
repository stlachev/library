package com.bookstore.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_fk", referencedColumnName="id")
    private Customer customer;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrdersList> orders = new HashSet<>();

    public void addOrdersList(OrdersList orders) {
        this.orders.add(orders);
    }

    public void removeOrdersList(OrdersList orders) {
        this.orders.remove(orders);
    }

    public void removeOrdersLists() {
        Iterator<OrdersList> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrdersList> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<OrdersList> orders) {
        this.orders = orders;
    }

}