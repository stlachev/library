package com.bookstore.library.entity.dto;

public class BookWithAuthorsDTO extends BookDTO {

    private AuthorNoBooksDTO author;

    public AuthorDTO getAuthor() {
        return this.author;
    }

}
