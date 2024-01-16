package com.bookstore.library.controller;

import java.rmi.ServerException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lib")
public class LibraryController {

    private final LibraryService libraryService;
    private final ModelMapper modelMapper;

    public LibraryController(LibraryService libraryService, ModelMapper modelMapper) {
        this.libraryService = libraryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) throws ServerException {
        return new ResponseEntity<>(libraryService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "/createwithauthorid/{id}")
    public ResponseEntity<BookDTO> createBookWithAuthorId(@Valid @RequestBody BookDTO bookDTO, Long id) throws ServerException {
        return new ResponseEntity<>(libraryService.createBookWithAuthorId(bookDTO, id), HttpStatus.CREATED);
    }

/*
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
*/
}
