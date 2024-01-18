package com.bookstore.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long > {

    @Query("SELECT a FROM Customer a WHERE a.customer_name = ?1")
    List<Customer> findByName(@NonNull String name);
    void deleteById(@NonNull Long id);

}
