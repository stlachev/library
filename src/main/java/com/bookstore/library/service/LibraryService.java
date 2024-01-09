package com.bookstore.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.AuthorRepository;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;

    public LibraryService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    public void insertAuthorWithBooks() {

        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);

        Book book = new Book();
        book.setGenre("Fantasy");
        book.setTitle("The book");

        author.addBook(book);

        authorRepository.save(author);
    }

    @Transactional
    public void deleteBookOfAuthor() {

        Author author = authorRepository.fetchByName("Alicia Tom");
        Book book = author.getBooks().get(0);

        author.removeBook(book); // use removeBook() helper
    }

    @Transactional
    public void deleteAllBooksOfAuthor() {
        Author author = authorRepository.fetchByName("J. N.");
        author.removeBooks(); // use removeBooks() helper
    }

}
