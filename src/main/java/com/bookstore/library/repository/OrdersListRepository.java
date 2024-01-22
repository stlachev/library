package com.bookstore.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.library.entity.Orders;
import com.bookstore.library.entity.OrdersList;


@Repository
public interface OrdersListRepository extends JpaRepository<OrdersList, Long> {

    List<OrdersList> findByOrder(Orders order);
    OrdersList findByOrderId(Long id);
}
