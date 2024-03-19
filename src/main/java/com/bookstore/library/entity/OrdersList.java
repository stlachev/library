package com.bookstore.library.entity;

import java.io.Serializable;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderslist")
public class OrdersList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @BatchSize(size=10)
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName="id")
    private Orders order;

    @BatchSize(size=10)
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName="id")
    private Book book;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transactional(readOnly= true)
    public Orders getOrder() {
        return this.order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Transactional(readOnly= true)
    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
/*
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", order='" + getOrder() + "'" +
            "}";
    }
*/
}