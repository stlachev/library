package com.bookstore.library.entity.dto;

import java.util.List;

import com.bookstore.library.entity.Book;
import com.bookstore.library.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDTO {
    Long id;
//    Long order_id;
//    Long customer_id;
    Long book_id;
//    boolean is_out;
    private Customer customer;
    private List<Book> books;
}
