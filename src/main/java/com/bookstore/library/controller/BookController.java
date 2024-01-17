package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.service.BookService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAll() throws Exception {
        List<BookDTO> bookDTOList = bookService.findAll();
        return !bookDTOList.isEmpty() ?
                new ResponseEntity<>(bookDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) throws Exception {
        Optional<BookDTO> book = bookService.getById(id);
        return book.map(bookDTO -> new ResponseEntity<>(bookDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/setauthor/{id}")
    public ResponseEntity<BookDTO> setAuthor(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) throws ServerException {
        BookDTO updatedBookDTO = bookService.setAuthor(id, bookDTO);
        return (updatedBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<BookDTO> update(@Valid @RequestBody BookDTO bookDTO) throws Exception {
        BookDTO updatedBookDTO = bookService.update(bookDTO);
        return (updatedBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<BookDTO> deleteById(@PathVariable Long id) throws Exception {
        Optional<BookDTO> book = bookService.deleteBookById(id);
        return book.map(bookDTO -> new ResponseEntity<>(bookDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) throws ServerException {
        BookDTO createdBookDTO = bookService.createBook(bookDTO);
        return (createdBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<>(createdBookDTO, HttpStatus.CREATED);
    }

    @PostMapping(path = "/createwithauthorid/{id}")
    public ResponseEntity<BookDTO> createBookWithAuthorId(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) throws ServerException {
        return new ResponseEntity<>(bookService.createBookWithAuthorId(id ,bookDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "/deletebookauthor/{id}")
    public ResponseEntity<BookDTO> deleteBookAuthor(@PathVariable Long id) throws Exception {
        BookDTO bookDTO = bookService.deleteBookAuthor(id);
        return (bookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
