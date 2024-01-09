package com.bookstore.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.LibraryRepository;

@RestController
@RequestMapping("/")
public class LibraryController {
    private LibraryRepository libraryRepo;

    public LibraryController(LibraryRepository libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    @GetMapping
    public List<Book> findAll() {
        return libraryRepo.findAll();
    }

/*    @GetMapping
    public String home(Map<String,Object> model) {
        List<Book> books = libraryRepo.findAll();
        model.put("books", books);
        return "home";
    }*/

    @PostMapping
    public String submit(Book book) {
        libraryRepo.save(book);
        return "redirect:/";
    }

}
