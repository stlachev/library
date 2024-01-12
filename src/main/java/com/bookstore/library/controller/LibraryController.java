package com.bookstore.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.service.LibraryService;

@RestController
@RequestMapping("/lib")
public class LibraryController {
    private LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // curl http://localhost:8080/lib/add -d title=book -d ganre=x -d author=Me
    @PostMapping(path="/add")
    public @ResponseBody String addBook(@RequestParam String title, @RequestParam String ganre
        , @RequestParam String author) {
        return libraryService.addBook(title, ganre, author);
    }

    // curl http://localhost:8080/lib/delete -d title=book -d author=Me
    @PostMapping(path="/delete")
    public @ResponseBody String deleteBook(@RequestParam String title, @RequestParam String author) {
        return libraryService.deleteBook(title, author);
    }

    // curl http://localhost:8080/lib/update -d title=book -d author=Me -d newauthor=First
    @PostMapping(path="/update")
    public @ResponseBody String updateBook(@RequestParam String title, @RequestParam String author, @RequestParam String newauthor) {
        return libraryService.updateBook(title, author, newauthor);
    }

    @PostMapping(path="/insert")
    public void insertAuthorWithBooks() {
        libraryService.insertAuthorWithBooks();
    }

    // curl http://localhost:8080/lib/deleteofauthor -d author=Me
    @PostMapping(path="/deleteofauthor")
    public void deleteBookOfAuthor(@RequestParam String author){
        libraryService.deleteBookOfAuthor(author);
    }

    // curl http://localhost:8080/lib/deleteallofauthor -d author=Me
    @PostMapping(path="/deleteallofauthor")
    public void deleteAllBooksOfAuthor(@RequestParam String author){
        libraryService.deleteAllBooksOfAuthor(author);
    };

}
