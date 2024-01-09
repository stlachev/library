package com.bookstore.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Author;
import com.bookstore.library.repository.AuthorRepository;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Author> getAllAuthors() {
        // This returns a JSON or XML with the users
        return authorRepo.findAll();
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addAuthor(@RequestParam String name
        , @RequestParam int age) {
    // curl http://localhost:8080/author/add -d name=First -d age=36
    // curl http://localhost:8080/author/all
        if (authorRepo.fetchByName(name) != null)
            return "Exists";
        Author a = new Author();
        a.setName(name);
        a.setAge(age);
        authorRepo.save(a);
        return "Saved";
    }
}
