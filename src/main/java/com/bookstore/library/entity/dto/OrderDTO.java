package com.bookstore.library.entity.dto;

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
public class OrderDTO {
    Long id;
    Long order_id;
    Long customer_id;
    Long book_id;
    boolean is_out;
}
