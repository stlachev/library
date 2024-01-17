package com.bookstore.library.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Long order_id;
    float total;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", order_id='" + getOrder_id() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
}