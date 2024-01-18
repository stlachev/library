package com.bookstore.library.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.library.entity.dto.AuthorDTO;

import jakarta.validation.constraints.NotNull;

public interface AuthorService {

    public List<AuthorDTO> findAll();
    public Optional<AuthorDTO> get(@NotNull Long id);
    public AuthorDTO create(@NotNull AuthorDTO authorDto);
    public AuthorDTO update(@NotNull AuthorDTO authorDTO);
    public Optional<AuthorDTO> delete(@NotNull Long id);

    public List<AuthorDTO> findByName(@NotNull String authorName);
}