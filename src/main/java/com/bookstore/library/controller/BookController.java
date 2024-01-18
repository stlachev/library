package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getAll() throws Exception {
        List<BookDTO> bookDTOList = bookService.findAll();
        return bookDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> get(@PathVariable Long id) throws Exception {
        Optional<BookDTO> book = bookService.get(id);
        return book.map(bookDTO -> new ResponseEntity<>(bookDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) throws ServerException {
        BookDTO createdBookDTO = bookService.create(bookDTO);
        return (createdBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<>(createdBookDTO, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<BookDTO> update(@Valid @RequestBody BookDTO bookDTO) throws Exception {
        BookDTO updatedBookDTO = bookService.update(bookDTO);
        return (updatedBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> delete(@PathVariable Long id) throws Exception {
        Optional<BookDTO> book = bookService.delete(id);
        return book.map(bookDTO -> new ResponseEntity<>(bookDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

//-----------------------------

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDTO>> findByTitle(@PathVariable String title)  throws Exception {
        List<BookDTO> bookDTOList = bookService.findByTitle(title);
        return bookDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<BookDTO>> findByAuthor(@PathVariable Long id)  throws Exception {
        List<BookDTO> bookDTOList = bookService.findByAuthor(id);
        return bookDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @PutMapping("/setauthor/{id}")
    public ResponseEntity<BookDTO> setAuthor(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) throws ServerException {
        BookDTO updatedBookDTO = bookService.setAuthor(id, bookDTO);
        return (updatedBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @PostMapping("/createwithauthorid/{id}")
    public ResponseEntity<BookDTO> createBookWithAuthorId(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) throws ServerException {
        return new ResponseEntity<>(bookService.createBookWithAuthorId(id ,bookDTO), HttpStatus.CREATED);
    }

    @PostMapping("/deletebookauthor/{id}")
    public ResponseEntity<BookDTO> deleteBookAuthor(@PathVariable Long id) throws Exception {
        BookDTO bookDTO = bookService.deleteBookAuthor(id);
        return (bookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping("/deletebookbytitle/{title}")
    public ResponseEntity<BookDTO> deleteBookByTitle(@PathVariable String title) throws Exception {
        BookDTO bookDTO = bookService.deleteBookByTitle(title);
        return (bookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
