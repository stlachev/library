package com.bookstore.library.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthorDTO {
    private Long author_id;
    private String name;
    private Integer age;
}
