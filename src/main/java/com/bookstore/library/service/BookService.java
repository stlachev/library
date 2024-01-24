package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.entity.dto.BookWithAuthorsDTO;

import jakarta.validation.constraints.NotNull;

public interface BookService {

    //public List<BookDTO> findAll();
    public List<BookWithAuthorsDTO> findAll();
    public Optional<BookWithAuthorsDTO> get(@NotNull Long id);
    public BookDTO create(@NotNull BookDTO bookDTO);
    public BookDTO update(@NotNull BookDTO bookDTO);
    public Optional<BookDTO> delete(@NotNull Long id);

    public List<BookDTO> findByTitle(@NotNull String title);
    public List<BookDTO> findByAuthor(@NotNull Long id);
    public BookDTO setAuthor(@NotNull Long authorId, @NotNull BookDTO bookDTO);
    public BookDTO createBookWithAuthorId(@NotNull Long id, @NotNull BookDTO bookDTO);
    public BookDTO deleteBookAuthor(@NotNull Long id);
    public BookDTO deleteBookByTitle(@NotNull String title);
}
