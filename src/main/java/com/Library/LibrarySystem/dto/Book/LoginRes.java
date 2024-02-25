package com.Library.LibrarySystem.dto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRes {
    private String email;
    private String token;
}
