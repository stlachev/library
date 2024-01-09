package com.bookstore.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Author;
import com.bookstore.library.repository.AuthorRepository;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping("/all")
    public List<Author> findAll() {
        return (List<Author>) authorRepo.findAll();
    }


}
