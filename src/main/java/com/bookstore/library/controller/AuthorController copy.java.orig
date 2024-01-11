package com.bookstore.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.AuthorRepository;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorRepository authorRepository;
//service

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // curl http://localhost:8080/author/all
    @GetMapping(path="/all")
    public @ResponseBody List<String> getAllAuthors() {
        List<Author> la = authorRepository.findAll();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
        }
        return ls;
    }

    // curl http://localhost:8080/author/read -d name=First
    @PostMapping(path="/read")
    public @ResponseBody String findAuthor(@RequestParam String name) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        return la.get(0).toString();
    }
    
    // curl http://localhost:8080/author/add -d name=First -d age=36
    @PostMapping(path="/add")
    public @ResponseBody String addAuthor(@RequestParam String name
        , @RequestParam int age) {
        if (!authorRepository.findByName(name).isEmpty())
            return "Author Exists";
        Author a = new Author();
        a.setName(name);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Saved: " + a.toString();
    }

//    curl http://localhost:8080/author/delete -d name=me
    @PostMapping(path="/delete")
    public @ResponseBody String deleteAuthor(@RequestParam String name) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        authorRepository.deleteById(la.get(0).getId());
        return "Author Deleted";
    }

    // curl http://localhost:8080/author/age -d name=First -d age=100
    @PostMapping(path="/age")
    public @ResponseBody String setAuthorAge(@RequestParam String name
        , @RequestParam int age) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        Author a = la.get(0);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Updated: " + a.toString();
    }

    // curl http://localhost:8080/author/name -d name=First -d newname=Second
    @PostMapping(path="/name")
    public @ResponseBody String setAuthorAge(@RequestParam String name
        , @RequestParam String newname) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        if (!authorRepository.findByName(newname).isEmpty())
            return "Author new name exists";
        Author a = la.get(0);
        a.setName(newname);
        a = authorRepository.save(a);
        return "Updated: " + a.toString();
    }

    // curl http://localhost:8080/author/books -d name=First
    @PostMapping(path="/books")
    public @ResponseBody List<String> getBooksFromAuthor(@RequestParam String name) {
        List<Author> la = authorRepository.findByName(name);
        List<String> ls = new ArrayList<>();
        if (la.isEmpty())
            return ls;
        Author author = la.get(0);
        List<Book> lb = author.getBooks();
        if (lb.isEmpty())
            return ls;
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    // curl http://localhost:8080/author/full
    @GetMapping(path="/full")
    public @ResponseBody List<String> getAll() {
        List<Author> la = authorRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
            List<Book> lb = la.get(i).getBooks();
            for (int ii = 0; ii < lb.size(); ii++) {
                ls.add("  -> " + lb.get(ii).toString());
            }
        }
        return ls;
    }


}
