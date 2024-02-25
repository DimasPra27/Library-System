package com.Library.LibrarySystem.controller;

import com.Library.LibrarySystem.domain.entity.Book;
import com.Library.LibrarySystem.dto.Book.BookDTO;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookRequest;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookResponse;
import com.Library.LibrarySystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public CreateOrUpdateBookResponse saveBook(@RequestBody CreateOrUpdateBookRequest request) {
        return bookService.saveBook(request);
    }

    @PutMapping("/{id}")
    public CreateOrUpdateBookResponse updateBook(@PathVariable("id") Long id, @RequestBody CreateOrUpdateBookRequest request) {
        return bookService.updateBook(id, request);
    }

    //Hard Delete Book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    //Soft Delete Book
    @PostMapping("/{id}")
    public void softDeleteBook(@PathVariable("id") Long id) {
        bookService.softDeleteBook(id);
    }
}
