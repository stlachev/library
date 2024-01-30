package com.bookstore.library.entity.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdersListDTO {

    protected Long id;

    @JsonIgnore
    protected OrdersDTO order;

    protected BookDTO book;
//    private BookWithAuthorsDTO book;

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

/*
    public BookWithAuthorsDTO getBook() {
        return this.book;
    }

    public void setBook(BookWithAuthorsDTO book) {
        this.book = book;
    }
*/

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", order='" + getOrder() + "'" +
            ", book='" + getBook() + "'" +
            "}";
    }

}