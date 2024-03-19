
package com.bookstore.library.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bookstore.library.entity.Author;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorRepositoryTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void AuthorRepository_SaveAll_ReturnSavedAuthor() {

        Author author = new Author();
        author.setName("Name");
        author.setAge(33);

        Author savedAuthor = authorRepository.save(author);

        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getAuthor_id()).isPositive();
    }

    @Test
    void AuthorRepository_GetAll_ReturnMoreThenOneAuthor() {
        Author author = new Author();
        author.setName("AuthorName");
        author.setAge(33);
        Author author2 = new Author();
        author.setName("AuthorName");
        author.setAge(33);

        authorRepository.save(author);
        authorRepository.save(author2);

        List<Author> authorList = authorRepository.findAll();

        Assertions.assertThat(authorList).isNotNull();
        int iSize = authorList.size();
        Assertions.assertThat(iSize).isEqualTo(2);
    }

    @Test
    void AuthorRepository_FindById_ReturnAuthor() {
        Author author = new Author();
        author.setName("John Doe");
        author.setAge(33);

        authorRepository.save(author);

        Author authorList = authorRepository.findById(author.getAuthor_id()).get();

        Assertions.assertThat(authorList).isNotNull();
    }

    @Test
    void AuthorRepository_FindByName_ReturnAuthorNotNull() {
        Author author = new Author();
        author.setName("Jane Doe");
        author.setAge(21);

        authorRepository.save(author);

        Author authorList = authorRepository.findByName(author.getName()).get(0);

        Assertions.assertThat(authorList).isNotNull();
    }

    @Test
    void AuthorRepository_UpdateAuthor_ReturnAuthorNotNull() {
        Author author = new Author();
        author.setName("Johnny");
        author.setAge(32);

        authorRepository.save(author);

        Author authorSave = authorRepository.findById(author.getAuthor_id()).get();
        authorSave.setAge(31);
        authorSave.setName("Tango");

        Author updatedAuthor = authorRepository.save(authorSave);

        Assertions.assertThat(updatedAuthor.getName()).isNotNull();
        Assertions.assertThat(updatedAuthor.getAge()).isPositive();
    }

    @Test
    void AuthorRepository_AuthorDelete_ReturnAuthorIsEmpty() {
        Author author = new Author();
        author.setName("Elton");
        author.setAge(32);

        authorRepository.save(author);

        authorRepository.deleteById(author.getAuthor_id());
        Optional<Author> authorReturn = authorRepository.findById(author.getAuthor_id());

        Assertions.assertThat(authorReturn).isEmpty();
    }

}

