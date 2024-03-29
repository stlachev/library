package com.bookstore.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Fetch(FetchMode.JOIN)
    @BatchSize(size=10)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_fk", referencedColumnName="id")
    private Customer customer;

    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size=10)
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrdersList> orders = new ArrayList<OrdersList>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transactional(readOnly= true)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Transactional(readOnly= true)
    public List<OrdersList> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersList> orders) {
        this.orders = orders;
    }

    //----
    public void addOrders(OrdersList orders) {
        this.orders.add(orders);
        orders.setOrder(this);
    }

    public void removeOrders(OrdersList orders) {
        orders.setOrder(null);
        this.orders.remove(orders);
    }

    public void removeOrdersLists() {
        Iterator<OrdersList> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            OrdersList orders = iterator.next();
            orders.setOrder(null);
            iterator.remove();
        }
    }

/*
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customer='" + getCustomer() + "'" +
            "}";
    }
*/
}