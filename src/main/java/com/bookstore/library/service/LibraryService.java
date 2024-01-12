package com.bookstore.library.service;

public interface LibraryService {

    public String addBook(String title, String ganre, String author);
    public String deleteBook(String title, String author);
    public String updateBook(String title, String author, String newauthor);

    public void insertAuthorWithBooks();
    public void deleteBookOfAuthor(String name);
    public void deleteAllBooksOfAuthor(String name);
}
