package com.bookstore.library.entity.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthorNoBooksDTO extends AuthorDTO {

    @JsonIgnore
    private Set<BookWithAuthorsDTO> books = new HashSet<>();

}
