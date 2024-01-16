package com.bookstore.library.entity.dto;

//import com.bookstore.library.entity.Author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDTO {
    private Long id;
    private String title;
    private String genre;
    private AuthorDTO author;
    private Long qty;
    private float price;
}
