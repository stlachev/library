package com.bookstore.library.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.service.AuthorService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
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
        Author createdAuthor = authorService.createAuthor(authorDTO);
        AuthorDTO createdAuthorDTO = modelMapper.map(createdAuthor, AuthorDTO.class);
        return new ResponseEntity<>(createdAuthorDTO, HttpStatus.CREATED);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<AuthorDTO> deleteAuthorById(@PathVariable Long id) throws Exception {
        Optional<AuthorDTO> author = authorService.deleteAuthorById(id);
        return author.map(authorDTO -> new ResponseEntity<>(authorDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @Valid @RequestBody AuthorDTO authorDTO) throws Exception {
        AuthorDTO updatedAuthorDTO = authorService.update(id, authorDTO);
        return (updatedAuthorDTO == null) ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(updatedAuthorDTO, HttpStatus.OK);
        
//        authorDTO.setAuthor_id(id);
//        AuthorDTO updatedAuthorDTO = authorService.update(id, authorDTO);
//        return new ResponseEntity<>(updatedAuthorDTO, HttpStatus.OK);
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



/*

    // curl http://localhost:8080/author/all
    @GetMapping(path="/all")
    public @ResponseBody List<String> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // curl http://localhost:8080/author/read -d name=First
    @PostMapping(path="/read")
*    public @ResponseBody String findAuthor(@RequestParam String name) {
        return authorService.findAuthor(name);
    }
    
    // curl http://localhost:8080/author/add -d name=First -d age=36
    @PostMapping(path="/add")
    public @ResponseBody String addAuthor(@RequestParam String name, @RequestParam int age) {
        return authorService.addAuthor(name, age);
    }

//    curl http://localhost:8080/author/delete -d name=me
    @PostMapping(path="/delete")
    public @ResponseBody String deleteAuthor(@RequestParam String name) {
        return authorService.deleteAuthor(name);
    }

    // curl http://localhost:8080/author/age -d name=First -d age=100
    @PostMapping(path="/age")
    public @ResponseBody String setAuthorAge(@RequestParam String name, @RequestParam int age) {
        return authorService.setAuthorAge(name, age);
    }

    // curl http://localhost:8080/author/name -d name=First -d newname=Second
    @PostMapping(path="/name")
    public @ResponseBody String changeName(@RequestParam String name, @RequestParam String newname) {
        return authorService.changeName(name, newname);
    }

    // curl http://localhost:8080/author/books -d name=First
    @PostMapping(path="/books")
    public @ResponseBody List<String> getBooksFromAuthor(@RequestParam String name) {
        return authorService.getBooksFromAuthor(name);
    }

    // curl http://localhost:8080/author/full
    @GetMapping(path="/full")
    public @ResponseBody List<String> getAll() {
        return authorService.getAll();
    }

    // curl http://localhost:8080/author/test -d name=First
    @GetMapping(path="/test")
    public ResponseEntity<List<Author>> getAlltst() {
        try {
            List<Author> authors = authorService.getAlltst();
            return (authors.size() == 0) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

}
