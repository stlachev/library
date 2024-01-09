package com.bookstore.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/all")
    public List<Book> findAll() {
        return (List<Book>) bookRepo.findAll();
    }
    
}
