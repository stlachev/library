package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.entity.dto.BookWithAuthorsDTO;

import jakarta.validation.constraints.NotNull;

public interface BookService {

    public List<BookWithAuthorsDTO> findAll();
    public Optional<BookWithAuthorsDTO> get(@NotNull Long id);
    public BookWithAuthorsDTO create(@NotNull BookDTO bookDTO);
    public BookWithAuthorsDTO update(@NotNull BookDTO bookDTO);
    public Optional<BookWithAuthorsDTO> delete(@NotNull Long id);

    public List<BookWithAuthorsDTO> findByTitle(@NotNull String title);
    public List<BookWithAuthorsDTO> findByAuthor(@NotNull Long id);
    public BookWithAuthorsDTO setAuthor(@NotNull Long authorId, @NotNull BookDTO bookDTO);
    public BookWithAuthorsDTO createBookWithAuthorId(@NotNull Long id, @NotNull BookDTO bookDTO);
    public BookWithAuthorsDTO deleteBookAuthor(@NotNull Long id);
    public BookWithAuthorsDTO deleteBookByTitle(@NotNull String title);
}
