package com.bookstore.library.service;

import java.util.List;

public interface BookService {

    public List<String> getAllBooks();
    public String getBook(String title);
    public String deleteBook(String title);
}
