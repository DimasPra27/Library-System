package com.Library.LibrarySystem.dto.Book;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateOrUpdateBookRequest {
    private String name;
    private int quantity;
    private int price;
}
