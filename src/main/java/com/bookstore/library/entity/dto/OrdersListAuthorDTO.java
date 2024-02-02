package com.bookstore.library.entity.dto;

public class OrdersListAuthorDTO extends OrdersListDTO {
    private BookWithAuthorsDTO book;

    public BookWithAuthorsDTO getBook() {
        return this.book;
    }

    public void setBook(BookWithAuthorsDTO book) {
        this.book = book;
    }

}
