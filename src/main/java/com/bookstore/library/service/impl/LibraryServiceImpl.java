package com.bookstore.library.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.dto.AuthorDTO;
import com.bookstore.library.entity.dto.BookDTO;
import com.bookstore.library.repository.AuthorRepository;
import com.bookstore.library.repository.BookRepository;
import com.bookstore.library.service.LibraryService;

@Service
@Transactional
public class LibraryServiceImpl  implements LibraryService {

    @Autowired
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public LibraryServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, ModelMapper modelMapper) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

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

    @Override
    public BookDTO createBookWithAuthorId(BookDTO bookDTO, Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null)
            return null;
        Book book = bookRepository.findById(bookDTO.getId()).orElse(null);
        author.addBook(book);
        book.setAuthor(author);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

//-------------------------------------------------------------------------

/*
    @Override
    public BookDTO createBookWithAuthorName(BookDTO bookDTO, String name) {

        Author author = authorRepository.findByName(name);
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

/*
    public String addBook(String title, String ganre, String author) {
        if (author.isEmpty())
            return "Empty";
        List<Author> la = authorRepository.findByName(author);
        if (la.isEmpty())
            return "Author not found";
        Book b = new Book();
        b.setTitle(title);
        b.setGenre(ganre);
        Author au = la.get(0);
        List<Book> lb = au.getBooks();
        for (int i = 0; i < lb.size(); i++) {
            if (lb.get(i).getTitle().equals(title))
                return "Book from the author exist";
        }
        au.addBook(b);
        b.setAuthor(au);
        b = bookRepository.save(b);
        return "Saved: " + b.toString();
    }

    public String deleteBook(String title, String author) {
        if (author.isEmpty())
            return "Empty";
        List<Author> la = authorRepository.findByName(author);
        if (la.isEmpty())
            return "Author not found";
        Author au = la.get(0);
        List<Book> lb = au.getBooks();
        Book book;
        for (int i = 0; i < lb.size(); i++) {
            book = lb.get(i);
            if (book.getTitle().equals(title)) {
                bookRepository.deleteById(book.getId());
                return "Book Deleted";
            }
        }
        return "Book not found";
    }

    public String updateBook(String title, String author, String newauthor) {
        if (author.isEmpty())
            return "Empty";
        List<Author> la = authorRepository.findByName(author);
        if (la.isEmpty())
            return "Author not found";
        if (newauthor.isEmpty())
            return "Empty";
        List<Author> lan = authorRepository.findByName(newauthor);
        if (lan.isEmpty()) {
            return "New Author not found";
        }
        Author au = la.get(0);
        List<Book> lb = au.getBooks();
        Book book;
        for (int i = 0; i < lb.size(); i++) {
            book = lb.get(i);
            if (book.getTitle().equals(title)) {
                Author newAuthor = lan.get(0);
                book.setAuthor(newAuthor);
                return "Updated: " + bookRepository.save(book).toString();
            }
        }
        return "Book not found";
    }

    public void insertAuthorWithBooks() {

        Author author = new Author();
        author.setName("Tom");
        author.setAge(38);

        Book book = new Book();
        book.setGenre("Fantasy");
        book.setTitle("Book");

        author.addBook(book);

        authorRepository.save(author);
    }

    public void deleteBookOfAuthor(String name) {

        Author author = authorRepository.fetchByName(name);
        Book book = author.getBooks().get(0);
        author.removeBook(book);
    }

    public void deleteAllBooksOfAuthor(String name) {
        Author author = authorRepository.fetchByName(name);
        author.removeBooks();
    }
*/
}
