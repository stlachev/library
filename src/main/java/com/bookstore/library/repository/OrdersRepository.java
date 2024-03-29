package com.bookstore.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Orders;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
//    List<Orders> findOrdersByOrderId(Long id);
    List<Orders> findOrdersByCustomerId(Long id);
    Optional<Orders> findById(Long id);
    List<Orders> findAll();
}
