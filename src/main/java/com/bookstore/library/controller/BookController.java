//**************************************************** */
//                  Not used
//***************************************************** */
package com.bookstore.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookRepository bookRepository;
//    private AuthorRepository authorRepo;

    public BookController(BookRepository bookRepository) { //, AuthorRepository authorRepo) {
        this.bookRepository = bookRepository;
//        this.authorRepo = authorRepo;
    }

    @GetMapping(path="/allbooks")
    public @ResponseBody  List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping(path="/getbook")
    public @ResponseBody List<Book> getBook(@RequestParam String title) {
    // curl http://localhost:8080/book/getbook -d title=book
    // curl http://localhost:8080/book/allbooks
        List<Book> bl = bookRepository.findByTitle(title);

        return bl;
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addBook(@RequestParam String title, @RequestParam String ganre
        , @RequestParam String author) {
    // curl http://localhost:8080/book/add -d title=book -d ganre=x -d author=Me
    // curl http://localhost:8080/book/all
//        AuthorRepository authorRepo;
//        List<Author> au_id = authorRepo.findByName(author);
//        if (au_id.isEmpty())
//            return "author not found";
        Book b = new Book();
        b.setTitle(title);
        b.setGenre(ganre);
//        Author au = au_id.get(0);
        //Author au = new Author();
        //au.addBook(b);
        //au.setName(author);
//        b.setAuthor(au);
        b = bookRepository.save(b);
        return "Saved: " + b.toString();
    }

}
