package com.bookstore.library.repository;
//**************************************************** */
//                  Not used
//***************************************************** */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Author;

@Repository
public interface LibraryRepository extends JpaRepository<Author, Long> {
//public interface LibraryRepository extends ListCrudRepository<Book, Long> {

    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = ?1")
    Author fetchByName(String name);
}