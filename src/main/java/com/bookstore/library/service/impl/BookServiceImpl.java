package com.bookstore.library.service.impl;

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
import com.bookstore.library.entity.dto.BookWithAuthorsDTO;
import com.bookstore.library.repository.AuthorRepository;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.service.BookService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class BookServiceImpl  implements BookService {

    @Autowired
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookWithAuthorsDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookWithAuthorsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookWithAuthorsDTO> get(@NotNull Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return (book.isPresent()) ?
                book.map(bookOp -> modelMapper.map(book, BookWithAuthorsDTO.class)) :
                Optional.empty();
    }

    @Override
    public BookDTO create(@NotNull BookDTO bookDTO) {
        Book returnBook;
        if (bookDTO.getAuthor() != null && bookDTO.getAuthor().getAuthor_id() != null){
            AuthorDTO dtoAuthor = bookDTO.getAuthor();
            bookDTO.setAuthor(null);
            Book newBook = modelMapper.map(bookDTO, Book.class);
            returnBook = bookRepository.save(newBook);
            setBookAuthor(returnBook.getId(), dtoAuthor.getAuthor_id());
        }else{
            Book newBook = modelMapper.map(bookDTO, Book.class);
            returnBook = bookRepository.save(newBook);
        }
        return modelMapper.map(returnBook, BookDTO.class);
    }

    @Override
    public BookDTO update(@NotNull BookDTO bookDTO) {
    //    Book book = bookRepository.findById(bookDTO.getId()).orElse(null);
    //    if (book == null) {
    //        return null;
    //    }
    //    modelMapper.map(bookDTO, book);
        Book book = modelMapper.map(bookDTO, Book.class);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Optional<BookDTO> delete(@NotNull Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return book.map(bookOp -> modelMapper.map(book, BookDTO.class));
        } else {
            return Optional.empty();
        }
    }

//------------------------------

    private void setBookAuthor(@NotNull Long bookId, @NotNull Long authorId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setAuthor(author);
        bookRepository.save(book);
    }

//-----------------------------

    @Override
    public List<BookDTO> findByTitle(@NotNull String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByAuthor(@NotNull Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO setAuthor(@NotNull Long authorId, @NotNull BookDTO bookDTO) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            return null;
        }
        Book book = bookRepository.findById(bookDTO.getId()).orElse(null);
        if (book == null) {
            return null;
        }
        book.setAuthor(author);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO createBookWithAuthorId(@NotNull Long id, @NotNull BookDTO bookDTO) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null)
            return null;
        Book newBook = modelMapper.map(bookDTO, Book.class);
        author.addBook(newBook);
        newBook.setAuthor(author);
        newBook = bookRepository.save(newBook);
        return modelMapper.map(newBook, BookDTO.class);
    }

    @Override
    public BookDTO deleteBookAuthor(@NotNull Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null)
            return null;
        book.setAuthor(null);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO deleteBookByTitle(@NotNull String title) {
        List<Book> lb = bookRepository.findByTitle(title);
        if (lb.isEmpty())
            return null;
        Book book = lb.get(0);
        bookRepository.deleteById(book.getId());
        return modelMapper.map(book, BookDTO.class);
    }

}
