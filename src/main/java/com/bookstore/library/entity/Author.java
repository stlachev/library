package com.bookstore.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "authors")
@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author_id;

    private String name;
    private int age;

    @BatchSize(size=10)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books =  new ArrayList<Book>();

    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        book.setAuthor(null);
        this.books.remove(book);
    }

    public void removeBooks() {
        Iterator<Book> iterator = this.books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            book.setAuthor(null);
            iterator.remove();
        }
    }

    @Transactional(readOnly= true)
    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    @Transactional(readOnly= true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transactional(readOnly= true)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Transactional(readOnly= true)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
/*
    @Override
    public String toString() {
        return "{" +
            " author_id='" + getAuthor_id() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            ", books='" + getBooks() + "'" +
            "}";
    }
*/
}
