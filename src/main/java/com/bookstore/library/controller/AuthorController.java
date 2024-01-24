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

import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.service.AuthorService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<List<AuthorDTO>> getAll() throws Exception {
        List<AuthorDTO> authorDTOList = authorService.findAll();
        return authorDTOList.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(authorDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> get(@PathVariable Long id) throws Exception {
        Optional<AuthorDTO> author = authorService.get(id);
        return author.map(authorOp -> new ResponseEntity<>(authorOp, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorDTO authorDTO) throws ServerException {
        AuthorDTO createdAuthorDTO = authorService.create(authorDTO);
        return new ResponseEntity<>(createdAuthorDTO, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<AuthorDTO> update(@Valid @RequestBody AuthorDTO authorDTO) throws Exception {
        AuthorDTO updatedAuthorDTO = authorService.update(authorDTO);
        return (updatedAuthorDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedAuthorDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable Long id) throws Exception {
        Optional<AuthorDTO> author = authorService.delete(id);
        return author.map(authorOp -> new ResponseEntity<>(authorOp, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

//-----------------------------

    @GetMapping("/find/{name}")
    public ResponseEntity<List<AuthorDTO>> findByName(@PathVariable String name)  throws Exception {
        List<AuthorDTO> listAuthorDTO = authorService.findByName(name);
        return (listAuthorDTO == null) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(listAuthorDTO, HttpStatus.OK);
    }
}
