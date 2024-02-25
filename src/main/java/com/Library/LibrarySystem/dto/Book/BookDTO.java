package com.Library.LibrarySystem.dto.Book;

import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {
    private Date createdAt;
    private String name;
    private int quantity;
    private int price;
}
