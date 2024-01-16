package com.bookstore.library.service;

import com.bookstore.library.entity.dto.BookDTO;

import jakarta.validation.constraints.NotNull;

public interface LibraryService {

    public void setBookAuthor(@NotNull Long bookId, @NotNull Long authorId);
    public BookDTO createBook(@NotNull BookDTO bookDTO);
    public BookDTO createBookWithAuthorId(BookDTO bookDTO, Long id);

/*
    public BookDTO createBookWithAuthorName(BookDTO bookDTO, String author);
    public String addBook(String title, String ganre, String author);
    public String deleteBook(String title, String author);
    public String updateBook(String title, String author, String newauthor);
    public void insertAuthorWithBooks();
    public void deleteBookOfAuthor(String name);
    public void deleteAllBooksOfAuthor(String name);
*/
}
