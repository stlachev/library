package com.bookstore.library.service;

import java.util.List;

public interface AuthorService {

    public List<String> getAllAuthors();
    public String findAuthor( String name);
    public String addAuthor(String name, int age);
    public String deleteAuthor(String name);
    public String setAuthorAge(String name, int age);
    public String changeName(String name, String newname);
    public List<String> getBooksFromAuthor(String name);
    public List<String> getAll();

    public List<String> getAlltst();
}
