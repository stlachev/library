package com.bookstore.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.service.BookService;


@Service
@Transactional
public class BookServiceImpl  implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<String> getAllBooks() {
        List<Book> lb = bookRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    public String getBook(String title) {
        List<Book> bl = bookRepository.findByTitle(title);
        if (bl.isEmpty())
            return "Book not found";
        Book book = bl.get(0);
        return book.toString() + "\n" + book.getAuthor().toString() + "\n";
    }

    public String deleteBook(String title) {
        List<Book> lb = bookRepository.findByTitle(title);
        if (lb.isEmpty())
            return "Book not found";
        Book book = lb.get(0);
        bookRepository.deleteById(book.getId());
        return "Book Deleted";
    }
    
}
