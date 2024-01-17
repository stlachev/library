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
    public BookDTO update(@NotNull BookDTO bookDTO);
    public BookDTO createBook(@NotNull BookDTO bookDTO);
    public BookDTO createBookWithAuthorId(@NotNull Long id, @NotNull BookDTO bookDTO);
    public BookDTO setAuthor(@NotNull Long authorId, @NotNull BookDTO bookDTO);
    public BookDTO deleteBookAuthor(@NotNull Long id);
}
