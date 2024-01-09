package com.bookstore.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
