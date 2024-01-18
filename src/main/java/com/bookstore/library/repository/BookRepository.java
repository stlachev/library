package com.bookstore.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    void deleteById(@NonNull Long idLong);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(Author author);

//    @Modifying
//    @Query("delete from Book a where a.title=:title")
//    void deleteBooks(@Param("title") String title);

}