package com.bookstore.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.Orders;
import com.bookstore.library.entity.OrdersList;

@Repository
public interface OrdersListRepository extends JpaRepository<OrdersList, Long> {

    List<OrdersList> findByOrderId(@NonNull Long id);
    List<OrdersList> findByBookId(@NonNull Long id);
    List<OrdersList> findByBook(@NonNull Book book);
    List<OrdersList> findByOrder(@NonNull Orders order);

}
