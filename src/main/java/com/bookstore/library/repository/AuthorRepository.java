package com.bookstore.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
        
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = ?1")
    Author fetchByName(String name);
    Author findByName(@NonNull String name);
    void deleteById(@NonNull Long id);
}
