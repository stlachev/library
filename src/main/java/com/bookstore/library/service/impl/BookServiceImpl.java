package com.bookstore.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.service.BookService;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class BookServiceImpl  implements BookService {

    @Autowired
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getById(@NotNull Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.map(bookOp -> modelMapper.map(book, BookDTO.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<BookDTO> deleteBookById(@NotNull Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return book.map(bookOp -> modelMapper.map(book, BookDTO.class));
        } else {
            return Optional.empty();
        }
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

    @Override
    public BookDTO update(@NotNull Long id, @NotNull BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getId()).orElse(null);
        if (book == null) {
            return null;
        }
        modelMapper.map(bookDTO, book);
        bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

//-----------------------------------------------------------------------------
/*
    @Override
    public Optional<BookDTO> update(Long id, @NotNull BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(bookDTO.getId());
        if (!book.isPresent()) {
            return Optional.empty();
        }
        modelMapper.map(bookDTO, book);
        bookRepository.save(book);
        return book.map(bookOp -> modelMapper.map(book, BookDTO.class));
    }
*/
        
//        return (book.isPresent()) ?
//            bookRepository.deleteById(id) :
//            Optional.empty();
/*
        if (book.isPresent()) {
            return book.map(bookOp -> modelMapper.map(book, BookDTO.class));
        } else {
            return Optional.empty();
        }
        bookRepository.findById(id).ifPresent(bookFromRepo -> bookRepository.deleteById(id));
    }*/
/*
    @Override
    public List<BookDTO> deleteBooks(String title) {
        List<Book> books = bookRepository.deleteBooks(title);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
*/
/*
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book returnBook;
        if (bookDTO.getAuthor() != null && bookDTO.getAuthor().getId() != null){
            AuthorDTO dtoAuthor = bookDTO.getAuthor();
            bookDTO.setAuthor(null);
            Book newBook = modelMapper.map(bookDTO, Book.class);
            returnBook = bookRepository.save(newBook);
            setBookAuthor(returnBook.getId(), dtoAuthor.getId());
        }else{
            Book newBook = modelMapper.map(bookDTO, Book.class);
            returnBook = bookRepository.save(newBook);
        }

        return modelMapper.map(returnBook, BookDTO.class);
    }
*/


/*
    @Override
    public void setBookAuthor(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
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
*/

//    @Override
//    public void deleteBookById(Long id) {
//        bookRepository.findById(id).ifPresent(bookFromRepo -> bookRepository.deleteById(id));
//    }
/*    @Override
    public BookDTO update(BookDTO bookDTO) {
        return null;
    }
*/

/*
    public List<String> getAllBooks() {
        List<Book> lb = bookRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    public String getBook(String title) {
        List<Book> bl = bookRepository.findByTitle(title);
        if (bl.isEmpty())
            return "Book not found";
        Book book = bl.get(0);
        return book.toString() + "\n" + book.getAuthor().toString() + "\n";
    }

    public String deleteBook(String title) {
        List<Book> lb = bookRepository.findByTitle(title);
        if (lb.isEmpty())
            return "Book not found";
        Book book = lb.get(0);
        bookRepository.deleteById(book.getId());
        return "Book Deleted";
    }
*/
}
