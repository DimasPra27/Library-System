package com.Library.LibrarySystem.service;

import com.Library.LibrarySystem.domain.entity.Book;
import com.Library.LibrarySystem.dto.Book.BookDTO;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookRequest;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookResponse;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    BookDTO getBookById(Long id);

    CreateOrUpdateBookResponse saveBook(CreateOrUpdateBookRequest request);

    CreateOrUpdateBookResponse updateBook(Long id, CreateOrUpdateBookRequest request);

    void updateQuantity(Long id, int Quantity);

    void deleteBook(Long id);

    void softDeleteBook(Long id);

    Book findById(Long id);
}
