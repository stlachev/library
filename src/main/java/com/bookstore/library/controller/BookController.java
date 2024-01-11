package com.bookstore.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //----------------- books -----------------------
    // curl http://localhost:8080/book/all
    @GetMapping(path="/all")
    public @ResponseBody List<String> getAllBooks() {
        List<Book> lb = bookRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    // curl http://localhost:8080/book/get -d title=book
    @PostMapping(path="/get")
    public @ResponseBody String getBook(@RequestParam String title) {
        List<Book> bl = bookRepository.findByTitle(title);
        if (bl.isEmpty())
            return "Book not found";
        Book book = bl.get(0);
        return book.toString() + "\n" + book.getAuthor().toString() + "\n";
    }

    // curl http://localhost:8080/book/delete -d title=book
    @PostMapping(path="/delete")
    public @ResponseBody String deleteBook(@RequestParam String title) {
        List<Book> lb = bookRepository.findByTitle(title);
        if (lb.isEmpty())
            return "Book not found";
        Book book = lb.get(0);
        bookRepository.deleteById(book.getId());
        return "Book Deleted";
    }
}
