package com.bookstore.library.entity.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdersListDTO {

    private Long id;
    private OrdersDTO order;
    private BookDTO book;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdersDTO getOrder() {
        return this.order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    public BookDTO getBook() {
        return this.book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}