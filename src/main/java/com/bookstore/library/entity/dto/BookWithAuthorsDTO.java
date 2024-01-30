package com.bookstore.library.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookWithAuthorsDTO extends BookDTO {

    @JsonIgnore
    private AuthorNoBooksDTO author;

    public AuthorNoBooksDTO getBookAuthor() {
        return this.author;
    }

    public void setBookAuthor(AuthorNoBooksDTO author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", genre='" + getGenre() + "'" +
            ", qty='" + getQty() + "'" +
            ", price='" + getPrice() + "'" +
            ", author='" + getAuthor() + "'" +
            "}";
    }

}
