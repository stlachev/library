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

import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.service.AuthorService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAll() throws Exception {
        List<AuthorDTO> authorDTOList = authorService.findAll();
        return !authorDTOList.isEmpty() ?
                new ResponseEntity<>(authorDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Long id) throws Exception {
        Optional<AuthorDTO> author = authorService.getById(id);
        return author.map(authorDTO -> new ResponseEntity<>(authorDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) throws ServerException {
        AuthorDTO createdAuthorDTO = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(createdAuthorDTO, HttpStatus.CREATED);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<AuthorDTO> deleteAuthorById(@PathVariable Long id) throws Exception {
        Optional<AuthorDTO> author = authorService.deleteAuthorById(id);
        return author.map(authorDTO -> new ResponseEntity<>(authorDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<AuthorDTO> update(@Valid @RequestBody AuthorDTO authorDTO) throws Exception {
        AuthorDTO updatedAuthorDTO = authorService.update(authorDTO);
        return (updatedAuthorDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedAuthorDTO, HttpStatus.OK);
    }

    @GetMapping(path="/name/{name}")
    public ResponseEntity<AuthorDTO> findByName(@PathVariable String name)  throws Exception {
        AuthorDTO authorDTO = authorService.findByName(name);
        return (authorDTO == null) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(authorDTO, HttpStatus.OK);
    }

    @GetMapping(path="/books/{name}")
    public ResponseEntity <List<BookDTO>> getBooksFromAuthor(@PathVariable String name) {
        List<BookDTO> bookDTOList = authorService.getBooksFromAuthor(name);
        return (!bookDTOList.isEmpty()) ?
                new ResponseEntity<>(bookDTOList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
