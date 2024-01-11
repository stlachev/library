package com.bookstore.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.service.AuthorService;
@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // curl http://localhost:8080/author/all
    @GetMapping(path="/all")
    public @ResponseBody List<String> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // curl http://localhost:8080/author/read -d name=First
    @PostMapping(path="/read")
    public @ResponseBody String findAuthor(@RequestParam String name) {
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

    @GetMapping(path="/test")
    public @ResponseBody List<String> getAlltst() {
        return authorService.getAlltst();
    }

}
