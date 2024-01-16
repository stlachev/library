package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.BookDTO;

import jakarta.validation.constraints.NotNull;

public interface BookService {

    public List<BookDTO> findAll();
    public Optional<BookDTO> getById(@NotNull Long id);
    public Optional<BookDTO> deleteBookById(@NotNull Long id);
    public BookDTO deleteBookByTitle(@NotNull String title);
    public BookDTO update(@NotNull Long id, @NotNull BookDTO bookDTO);


//    public List<BookDTO> deleteBooks(String title);
//    public void deleteBookOfAuthor();
//    public BookDTO createBook(BookDTO bookDTO);
//    public List<String> getAllBooks();
//    public String getBook(String title);
//    public String deleteBook(String title);
}
