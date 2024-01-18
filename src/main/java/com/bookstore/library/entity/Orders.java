package com.bookstore.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    Long order_id;
//    Long customer_id;
//    Long book_id;
//    boolean is_out;

//---------------------------------
    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", referencedColumnName="id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<Book> books;// = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="book_id", referencedColumnName="id")
//    private List<Book> books = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
//    private List<Book> books = new ArrayList<>();
    

    public Orders() {
        this.customer = null;
        this.books = new ArrayList<>();
    }

//    public Orders(Long id, Customer customer, List<Book> books) {
//        this.id = id;
//        this.customer = customer;
//        this.books = books;
//    }

//---------------------------------
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBooks(Book book) {
        this.books.add(book);
    }

//    public void deleteBooks(Book book) {
//        this.books.delete();
//    }

//----------------------------------

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            "}";
    }

}