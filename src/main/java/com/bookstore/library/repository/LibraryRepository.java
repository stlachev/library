package com.bookstore.library.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Book;

@Repository
//public interface LibraryRepository extends ListCrudRepository<Book, Long> {
public interface LibraryRepository extends JpaRepository<Book, Long> {

//    List<Book> findAllByAuthor(String Name);
}