package com.bookstore.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
        
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = ?1")
    Author fetchByName(String name);
    List<Author> findByName(String name);
}
//-------------------------------------------------------------------------------------
//    @Modifying
//    @Query("update Author a set a.age = ?2 where a.author_id = ?1")
//    void updateAuthorAge(Long authorId, Integer age);
/*    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("SELECT author_id FROM Author WHERE name = '?1'")
    long getAuthorIdByName(String name);
*/
