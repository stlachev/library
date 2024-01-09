package com.bookstore.library.controller;

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
    private BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        // This returns a JSON or XML with the users
        return bookRepo.findAll();
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addBook(@RequestParam String title, @RequestParam String ganre
        , @RequestParam String author) {
    // curl http://localhost:8080/book/add -d title=book -d ganre=x -d author=Me
    // curl http://localhost:8080/book/all
    /*     AuthorRepository authorRepo;
        Author au = authorRepo.fetchByName(author);
        if (au == null)
            return "author missing";*/
        Book b = new Book();
        b.setTitle(title);
        b.setGenre(ganre);
        //Author au = new Author();
        //au.addBook(b);
        //au.setName(author);
        //b.setAuthor(au);
        bookRepo.save(b);
        return "Saved";
    }

}
