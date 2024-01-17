package com.bookstore.library.repository;
//**************************************************** */
//                  Not used
//***************************************************** */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Author;

@Repository
public interface LibraryRepository extends JpaRepository<Author, Long> {

}