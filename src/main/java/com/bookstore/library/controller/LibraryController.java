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
import com.bookstore.library.repository.BookRepository;

@RestController
@RequestMapping("/lib")
public class LibraryController {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public LibraryController(
        BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody List<String> getAll() {
    // curl http://localhost:8080/lib/all
        List<Author> la = authorRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
            List<Book> lb = bookRepository.findByAuthor(la.get(i));
            for (int ii = 0; ii < lb.size(); ii++) {
                ls.add("  -> " + lb.get(ii).toString());
            }
        }
        return ls;
    }

//----------------- books -----------------------
    @GetMapping(path="/allbooks")
    public @ResponseBody List<String> getAllBooks() {
    // curl http://localhost:8080/lib/allbooks
        List<Book> lb = bookRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    @PostMapping(path="/getbook")
    public @ResponseBody String getBook(@RequestParam String title) {
    // curl http://localhost:8080/lib/getbook -d title=book
        List<Book> bl = bookRepository.findByTitle(title);
        if (bl.isEmpty())
            return "Book not found";
        Book book = bl.get(0);
        return book.toString() + "\n" + book.getAuthor().toString() + "\n";
    }

    @PostMapping(path="/getbooks")
    public @ResponseBody List<String> getBooksFromAuthor(@RequestParam String name) {
    // curl http://localhost:8080/lib/getbooks -d name=First
        List<Author> la = authorRepository.findByName(name);
        List<String> ls = new ArrayList<>();
        if (la.isEmpty())
            return ls;
        Author author = la.get(0);
        List<Book> lb = author.getBooks();
//        List<Book> lb = bookRepository.findByAuthor(la.get(0));
        if (lb.isEmpty())
            return ls;
        for (int i = 0; i < lb.size(); i++) {
            ls.add("--> " + lb.get(i).toString());
        }
        return ls;
    }

    @PostMapping(path="/addbook")
    public @ResponseBody String addBook(@RequestParam String title, @RequestParam String ganre
        , @RequestParam String author) {
    // curl http://localhost:8080/lib/addbook -d title=book -d ganre=x -d author=Me
        List<Author> au_id = authorRepository.findByName(author);
        if (au_id.isEmpty())
            return "Author not found";
        Book b = new Book();
        b.setTitle(title);
        b.setGenre(ganre);
        Author au = au_id.get(0);
        au.addBook(b);
        b.setAuthor(au);
        b = bookRepository.save(b);
        return "Saved: " + b.toString();
    }

    @PostMapping(path="/deletebook")
    public @ResponseBody String deleteBook(@RequestParam String title) {
//    curl http://localhost:8080/lib/deletebook -d title=book
        List<Book> lb = bookRepository.findByTitle(title);
        if (lb.isEmpty())
            return "Book not found";
        Book book = lb.get(0);
        bookRepository.deleteById(book.getId());
        return "Book Deleted";
    }

//----------------- authors -----------------------
//Iterable<Author>

    @GetMapping(path="/allauthors")
    public @ResponseBody List<String> getAllAuthors() {
    // curl http://localhost:8080/lib/allauthors
        List<Author> la = authorRepository.findAll();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
        }
        return ls;
    }

    @PostMapping(path="/readauthor")
    public @ResponseBody String findAuthor(@RequestParam String name) {
    // curl http://localhost:8080/lib/readauthor -d name=First
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        return la.get(0).toString();
    }
    
    @PostMapping(path="/addauthor")
    public @ResponseBody String addAuthor(@RequestParam String name
        , @RequestParam int age) {
    // curl http://localhost:8080/lib/addauthor -d name=First -d age=36
        if (!authorRepository.findByName(name).isEmpty())
            return "Author Exists";
        Author a = new Author();
        a.setName(name);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Saved: " + a.toString();
    }

    @PostMapping(path="/deletauthor")
    public @ResponseBody String deleteAuthor(@RequestParam String name) {
//    curl http://localhost:8080/lib/deleteauthor -d name=me
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        authorRepository.deleteById(la.get(0).getId());
        return "Author Deleted";
    }

}
