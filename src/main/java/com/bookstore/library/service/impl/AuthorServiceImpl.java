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
        if (author.isPresent()) {
            return author.map(authorOp -> modelMapper.map(author, AuthorDTO.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Author createAuthor(@NotNull AuthorDTO authorDto) {
        Author newAuthor = modelMapper.map(authorDto, Author.class);
        return authorRepository.save(newAuthor);
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
    public AuthorDTO update(@NotNull Long id, @NotNull AuthorDTO authorDTO) {
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
//------------------------------------------------------------------------------
//        if (author != null) {
//            return modelMapper.map(author, AuthorDTO.class);
//        }
//
//        if (lb.isEmpty())
//            return ls;
//
//        BookDTO bb;
//        for (int i = 0; i < lb.size(); i++) {
//            bb =modelMapper.map(book, BookDTO.class);
//            ls.add(lb.get(i).toString());
//        }
//
//        return ls;
    
//    public List<String> getAllAuthors() {
//       List<Author> la = authorRepository.findAll();
//        List<String> ls = new ArrayList<>();
//        for (int i = 0; i < la.size(); i++) {
//            ls.add(la.get(i).toString());
//        }
//        return ls;
//    }

//--------------------------------------------------
/*
    public String findAuthor(String name) {
        if (name.isEmpty())
            return "Empty";
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        return la.get(0).toString();
    }
*/
/*
    public String addAuthor(String name, int age) {
        if (name.isEmpty())
            return "Empty";
        if (!authorRepository.findByName(name).isEmpty())
            return "Author Exists";
        Author a = new Author();
        a.setName(name);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Saved: " + a.toString();
    }

    public String deleteAuthor(String name) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        authorRepository.deleteById(la.get(0).getId());
        return "Author Deleted";
    }

    public String setAuthorAge(String name, int age) {
        if (name.isEmpty())
            return "Empty";
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        Author a = la.get(0);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Updated: " + a.toString();
    }

    public String changeName(String name, String newname) {
        if (name.isEmpty())
            return "Empty";
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        if (newname.isEmpty())
            return "Empty";
        if (!authorRepository.findByName(newname).isEmpty())
            return "Author new name exists";
        Author a = la.get(0);
        a.setName(newname);
        a = authorRepository.save(a);
        return "Updated: " + a.toString();
    }

    public List<String> getBooksFromAuthor(String name) {
        List<String> ls = new ArrayList<>();
        if (name.isEmpty())
            return ls;
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return ls;
        Author author = la.get(0);
        List<Book> lb = author.getBooks();
        if (lb.isEmpty())
            return ls;
        for (int i = 0; i < lb.size(); i++) {
            ls.add(lb.get(i).toString());
        }
        return ls;
    }

    public List<String> getAll() {
        List<Author> la = authorRepository.findAll();
        List<String> ls =  new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
            List<Book> lb = la.get(i).getBooks();
            for (int ii = 0; ii < lb.size(); ii++) {
                ls.add("  -> " + lb.get(ii).toString());
            }
        }
        return ls;
    }
    
    public List<Author> getAlltst(){
        List<Author> author =  new ArrayList<Author>();
        List<Author> la = authorRepository.findAll();
        for (int i = 0; i < la.size(); i++) {
            author.add(la.get(i));
        }
        return authorRepository.findAll().stream().toList();
    }
*/


}
