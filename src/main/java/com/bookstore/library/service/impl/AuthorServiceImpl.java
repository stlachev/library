package com.bookstore.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.entity.dto.BookDTO;
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
    public Optional<AuthorDTO> getById(@NotNull Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return (author.isPresent()) ?
            author.map(authorOp -> modelMapper.map(author, AuthorDTO.class)) :
            Optional.empty();
    }

    @Override
    public AuthorDTO createAuthor(@NotNull AuthorDTO authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        author = authorRepository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public Optional<AuthorDTO> deleteAuthorById(@NotNull Long id) {
        Optional<Author> author =  authorRepository.findById(id);
        if (!author.isPresent()) {
            return Optional.empty();
        }
        authorRepository.deleteById(id);
        return author.map(authorOp -> modelMapper.map(author, AuthorDTO.class));
    }

    @Override
    public AuthorDTO update(@NotNull AuthorDTO authorDTO) {
        Author author = authorRepository.findById(authorDTO.getAuthor_id()).orElse(null);
        if (author == null) {
            return null;
        }
        modelMapper.map(authorDTO, author);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public AuthorDTO findByName(@NotNull String authorName) {
        Author author = authorRepository.findByName(authorName);
        if (author != null) {
            return modelMapper.map(author, AuthorDTO.class);
        }
        return null;
    }

    public void deleteById(@NotNull Long id) {
        authorRepository.deleteById(id);
    }

    public List<BookDTO> getBooksFromAuthor(@NotNull String name) {
        List<BookDTO> ls = new ArrayList<BookDTO>();
        if (name.isEmpty())
            return ls;
        Author author = authorRepository.findByName(name);
        if (author == null)
            return ls;

        List<Book> books = author.getBooks();
        return books.stream()
            .map(book -> modelMapper.map(book, BookDTO.class))
            .collect(Collectors.toList());
    }
}
