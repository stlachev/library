package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.entity.dto.BookDTO;

import jakarta.validation.constraints.NotNull;

public interface AuthorService {

    public List<AuthorDTO> findAll();
    public Optional<AuthorDTO> getById(@NotNull Long id);
    public AuthorDTO createAuthor(@NotNull AuthorDTO authorDto);
    public Optional<AuthorDTO> deleteAuthorById(@NotNull Long id);
    public AuthorDTO update(@NotNull AuthorDTO authorDTO);
    public AuthorDTO findByName(@NotNull String authorName);
    public List<BookDTO> getBooksFromAuthor(@NotNull String name);

/*  public void deleteById(Long id);
    public List<String> getAllAuthors();
    public String findAuthor( String name);
    public String addAuthor(String name, int age);
    public String deleteAuthor(String name);
    public String setAuthorAge(String name, int age);
    public String changeName(String name, String newname);
    public List<String> getBooksFromAuthor(String name);
    public List<String> getAll();
    public List<Author> getAlltst();
*/
}