package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.repository.AuthorRepository;
import com.bookstore.library.service.AuthorService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> get(@NotNull Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return (author.isPresent()) ?
            author.map(authorOp -> modelMapper.map(author, AuthorDTO.class)) :
            Optional.empty();
    }

    @Override
    public AuthorDTO create(@NotNull AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        author = authorRepository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public AuthorDTO update(@NotNull AuthorDTO authorDTO) {
    //    Author author = authorRepository.findById(authorDTO.getAuthor_id()).orElse(null);
    //    if (author == null) {
    //       return null;
    //    }
    //    modelMapper.map(authorDTO, author);
        Author author = modelMapper.map(authorDTO, Author.class);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public Optional<AuthorDTO> delete(@NotNull Long id) {
        Optional<Author> author =  authorRepository.findById(id);
        if (!author.isPresent()) {
            return Optional.empty();
        }
        authorRepository.deleteById(id);
        return author.map(authorOp -> modelMapper.map(author, AuthorDTO.class));
    }

//-------------------------------

    @Override
    public List<AuthorDTO> findByName(@NotNull String authorName) {
        List<Author> authors = authorRepository.findByName(authorName);
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }
}
