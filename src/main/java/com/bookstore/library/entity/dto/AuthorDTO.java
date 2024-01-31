package com.bookstore.library.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "author_id")
public class AuthorDTO {
    private Long author_id;
    private String name;
    private Integer age;

    private List<BookDTO> books = new ArrayList<BookDTO>();

    public Long getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "{" +
            " author_id='" + getAuthor_id() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }

}
