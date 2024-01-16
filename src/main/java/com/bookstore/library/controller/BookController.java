package com.bookstore.library.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
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

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) throws Exception {
        BookDTO updatedBookDTO = bookService.update(id, bookDTO);
        return (updatedBookDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<BookDTO> deleteById(@PathVariable Long id) throws Exception {
        Optional<BookDTO> book = bookService.deleteBookById(id);
        return book.map(bookDTO -> new ResponseEntity<>(bookDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

/*
    @PostMapping(path = "/delete/{title}")
    public ResponseEntity<List<BookDTO>> deleteByName(@PathVariable String title) throws Exception {
        List<BookDTO> bookDTOList = bookService.deleteBooks(title);
        return !bookDTOList.isEmpty()
                ? new ResponseEntity<>(bookDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
*/
/*    @PostMapping(path = "/create")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) throws ServerException {
        Book createdAuthor = bookService.createBook(bookDTO);
        AuthorDTO createdAuthorDTO = modelMapper.map(createdAuthor, AuthorDTO.class);
        return new ResponseEntity<>(createdAuthorDTO, HttpStatus.CREATED);
    }
*/

//    @PostMapping(path = "/create")
//    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) throws ServerException {
//        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
//    }


/*

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
*/
}
