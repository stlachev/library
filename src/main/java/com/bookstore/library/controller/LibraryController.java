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

    // curl http://localhost:8080/lib/addbook -d title=book -d ganre=x -d author=Me
    @PostMapping(path="/addbook")
    public @ResponseBody String addBook(@RequestParam String title, @RequestParam String ganre
        , @RequestParam String author) {
        List<Author> au_id = authorRepository.findByName(author);
        if (au_id.isEmpty())
            return "Author not found";
        Book b = new Book();
        b.setTitle(title);
        b.setGenre(ganre);
        Author au = au_id.get(0);
        List<Book> lb = au.getBooks();
        for (int i = 0; i < lb.size(); i++) {
            if (lb.get(i).getTitle().equals(title))
                return "Book from the author exist";
        }
        au.addBook(b);
        b.setAuthor(au);
        b = bookRepository.save(b);
        return "Saved: " + b.toString();
    }

    //----------------- books -----------------------
    // curl http://localhost:8080/lib/allbooks
    @GetMapping(path="/allbooks")
    public @ResponseBody List<String> getAllBooks() {
        List<Book> lb = bookRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    // curl http://localhost:8080/lib/getbook -d title=book
    @PostMapping(path="/getbook")
    public @ResponseBody String getBook(@RequestParam String title) {
        List<Book> bl = bookRepository.findByTitle(title);
        if (bl.isEmpty())
            return "Book not found";
        Book book = bl.get(0);
        return book.toString() + "\n" + book.getAuthor().toString() + "\n";
    }

//    curl http://localhost:8080/lib/deletebook -d title=book
    @PostMapping(path="/deletebook")
    public @ResponseBody String deleteBook(@RequestParam String title) {
        List<Book> lb = bookRepository.findByTitle(title);
        if (lb.isEmpty())
            return "Book not found";
        Book book = lb.get(0);
        bookRepository.deleteById(book.getId());
        return "Book Deleted";
    }

//----------------- authors -----------------------

    // curl http://localhost:8080/lib/allauthors
    @GetMapping(path="/allauthors")
    public @ResponseBody List<String> getAllAuthors() {
        List<Author> la = authorRepository.findAll();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
        }
        return ls;
    }

    // curl http://localhost:8080/lib/readauthor -d name=First
    @PostMapping(path="/readauthor")
    public @ResponseBody String findAuthor(@RequestParam String name) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        return la.get(0).toString();
    }
    
    // curl http://localhost:8080/lib/addauthor -d name=First -d age=36
    @PostMapping(path="/addauthor")
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

//    curl http://localhost:8080/lib/deleteauthor -d name=me
    @PostMapping(path="/deletauthor")
    public @ResponseBody String deleteAuthor(@RequestParam String name) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        authorRepository.deleteById(la.get(0).getId());
        return "Author Deleted";
    }

    // curl http://localhost:8080/lib/authorage -d name=First -d age=100
    @PostMapping(path="/authorage")
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

    // curl http://localhost:8080/lib/authorname -d name=First -d newname=Second
    @PostMapping(path="/authorname")
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

    // curl http://localhost:8080/lib/getbooks -d name=First
    @PostMapping(path="/getbooks")
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
            ls.add("--> " + lb.get(i).toString());
        }
        return ls;
    }

    // curl http://localhost:8080/lib/all
    @GetMapping(path="/all")
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
