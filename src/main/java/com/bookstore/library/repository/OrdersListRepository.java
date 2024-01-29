package com.bookstore.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.OrdersList;


@Repository
public interface OrdersListRepository extends JpaRepository<OrdersList, Long> {

//    OrdersList findByList(Long id);
//    OrdersList findByOrdersList(Long id);
}
