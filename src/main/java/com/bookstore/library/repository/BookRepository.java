package com.bookstore.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Author;
import com.bookstore.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(Author author);
}
//-------------------------------------------------------------------------------------
    //long deleteByTitle(String title);
/*    @Query("SELECT * FROM books a JOIN FETCH a.books WHERE a.name = ?1")
    default void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }*/


//    @Query("delete from Book b where b.title = ?1")
//    long deleteByTitle(String title);


/*
SELECT b.id, b.title, b.genre, name, age
    FROM authors a 
    INNER JOIN books b ON b.author_id = a.author_id;
*/