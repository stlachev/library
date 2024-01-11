package com.bookstore.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;
import com.bookstore.library.repository.AuthorRepository;
import com.bookstore.library.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<String> getAllAuthors() {
        List<Author> la = authorRepository.findAll();
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < la.size(); i++) {
            ls.add(la.get(i).toString());
        }
        return ls;
    }

    public String findAuthor(String name) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        return la.get(0).toString();
    }
    
    public String addAuthor(String name, int age) {
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
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        Author a = la.get(0);
        a.setAge(age);
        a = authorRepository.save(a);
        return "Updated: " + a.toString();
    }

    public String changeName(String name, String newname) {
        List<Author> la = authorRepository.findByName(name);
        if (la.isEmpty())
            return "Author not found";
        if (!authorRepository.findByName(newname).isEmpty())
            return "Author new name exists";
        Author a = la.get(0);
        a.setName(newname);
        a = authorRepository.save(a);
        return "Updated: " + a.toString();
    }

    public List<String> getBooksFromAuthor(String name) {
        List<Author> la = authorRepository.findByName(name);
        List<String> ls = new ArrayList<>();
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
    
    public List<String> getAlltst(){
        List<String> ls =  new ArrayList<>();
        return ls;
    }
    
}
