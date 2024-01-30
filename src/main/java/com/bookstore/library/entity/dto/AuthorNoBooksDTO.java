package com.bookstore.library.entity.dto;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthorNoBooksDTO extends AuthorDTO {

    @JsonIgnore
    private Collection<AuthorDTO> books = new HashSet<AuthorDTO>();

}
