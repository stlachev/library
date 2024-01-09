package com.bookstore.library.repository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Book;

@Repository
public interface LibraryRepository extends ListCrudRepository<Book, Long> {

}