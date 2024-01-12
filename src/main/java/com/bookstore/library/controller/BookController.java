package com.bookstore.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //----------------- books -----------------------
    // curl http://localhost:8080/book/all
    @GetMapping(path="/all")
    public @ResponseBody List<String> getAllBooks() {
        return bookService.getAllBooks();
    }

    // curl http://localhost:8080/book/get -d title=book
    @PostMapping(path="/get")
    public @ResponseBody String getBook(@RequestParam String title) {
        return bookService.getBook(title);
    }

    // curl http://localhost:8080/book/delete -d title=book
    @PostMapping(path="/delete")
    public @ResponseBody String deleteBook(@RequestParam String title) {
        return bookService.deleteBook(title);
    }
}
