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

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.AuthorRepository;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorRepository authorRepository;
//    private BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
//        this.bookRepository = bookRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping(path="/add")
    public @ResponseBody String addAuthor(@RequestParam String name
        , @RequestParam int age) {
    // curl http://localhost:8080/author/add -d name=First -d age=36
    // curl http://localhost:8080/author/all
        if (!authorRepository.findByName(name).isEmpty())
            return "Author Exists";
        Author a = new Author();
        a.setName(name);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Saved: ";// + a.toString();
    }

    @PostMapping(path="/addall")
    public @ResponseBody String addAll(@RequestParam String name
        , @RequestParam int age, @RequestParam String title, @RequestParam String genre) {
    // curl http://localhost:8080/author/addall -d name=name -d age=36 -d title=book1 -d ganre=xx

        Book b = new Book();
        b.setTitle(title);
        b.setGenre(genre);
        List<Author> au_id = authorRepository.findByName(name);
//        boolean bNewAuthor = true;
        Author au;
        if (au_id.isEmpty()) {
            au = new Author();
            au.setAge(age);
            au.setName(name);
//            au.addBook(b);
            au = authorRepository.save(au);
        } else {
            au = au_id.get(0);
//            bNewAuthor = false;
        }
        au.addBook(b);
//        b.setAuthor(au);
//        b = bookRepository.save(b);
//        if (bNewAuthor) {
//            au = authorRepository.save(au);
//        }

/*
        if (!authorRepository.findByName(name).isEmpty())
            return "Author Exists";
        Author author = new Author();
        author.setName(name);
        author.setAge(age);

        Book book = new Book();
        book.setGenre(genre);
        book.setTitle(title);
        book.setAuthor(author);

        author.addBook(book);

        author = authorRepository.save(author);
*/
        return "Saved: " + au.toString() + b.toString();
    }


}
