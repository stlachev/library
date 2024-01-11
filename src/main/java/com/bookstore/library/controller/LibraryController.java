package com.bookstore.library.controller;

import java.util.List;

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

    public LibraryController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // curl http://localhost:8080/lib/add -d title=book -d ganre=x -d author=Me
    @PostMapping(path="/add")
    public @ResponseBody String addBook(@RequestParam String title, @RequestParam String ganre
        , @RequestParam String author) {
        List<Author> la = authorRepository.findByName(author);
        if (la.isEmpty())
            return "Author not found";
        Book b = new Book();
        b.setTitle(title);
        b.setGenre(ganre);
        Author au = la.get(0);
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

    // curl http://localhost:8080/lib/delete -d title=book -d author=Me
    @PostMapping(path="/delete")
    public @ResponseBody String deleteBook(@RequestParam String title, @RequestParam String author) {
        List<Author> la = authorRepository.findByName(author);
        if (la.isEmpty())
            return "Author not found";
        Author au = la.get(0);
        List<Book> lb = au.getBooks();
        Book book;
        for (int i = 0; i < lb.size(); i++) {
            book = lb.get(i);
            if (book.getTitle().equals(title)) {
                bookRepository.deleteById(book.getId());
                return "Book Deleted";
            }
        }
        return "Book not found";
    }

    // curl http://localhost:8080/lib/update -d title=book -d author=Me -d newauthor=First
    @PostMapping(path="/update")
    public @ResponseBody String updateBook(@RequestParam String title, @RequestParam String author, @RequestParam String newauthor) {
        List<Author> la = authorRepository.findByName(author);
        if (la.isEmpty())
            return "Author not found";
        List<Author> lan = authorRepository.findByName(newauthor);
        if (lan.isEmpty()) {
            return "New Author not found";
        }
        Author au = la.get(0);
        List<Book> lb = au.getBooks();
        Book book;
        for (int i = 0; i < lb.size(); i++) {
            book = lb.get(i);
            if (book.getTitle().equals(title)) {
                Author newAuthor = lan.get(0);
                book.setAuthor(newAuthor);
                return "Updated: " + bookRepository.save(book).toString();
            }
        }
        return "Book not found";
    }

}
