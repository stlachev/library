package com.bookstore.library.entity.dto;

public class BookWithAuthorsDTO extends BookDTO {

    private AuthorNoBooksDTO author;

    public AuthorNoBooksDTO getNoBooksAuthor() {
        return this.author;
    }

    public void setNoBooksAuthor(AuthorNoBooksDTO author) {
        this.author = author;
    }

}
