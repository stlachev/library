package com.bookstore.library.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthorNoBooksDTO extends AuthorDTO {

    @JsonIgnore
    private List<AuthorDTO> books = new ArrayList<AuthorDTO>();

}
