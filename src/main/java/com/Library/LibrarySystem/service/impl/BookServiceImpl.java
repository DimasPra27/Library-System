package com.Library.LibrarySystem.service.impl;

import com.Library.LibrarySystem.domain.entity.Book;
import com.Library.LibrarySystem.dto.Book.BookDTO;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookRequest;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookResponse;
import com.Library.LibrarySystem.exception.ResourceNotFoundException;
import com.Library.LibrarySystem.exception.ValidationException;
import com.Library.LibrarySystem.mapper.BookMapper;
import com.Library.LibrarySystem.repository.BookRepository;
import com.Library.LibrarySystem.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findByDeletedAtIsNull();
    }

    @Override
    public BookDTO getBookById(Long id) {
        final Book book = bookRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        return bookMapper.convertBookToBookDTO(book);
    }

    @Override
    public CreateOrUpdateBookResponse saveBook(CreateOrUpdateBookRequest request) {
        log.info("Create book request ==> {}", request.toString());
        validateCreateOrUpdate(request);

        final Book book = new Book()
                .setName(request.getName())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice());

        bookRepository.save(book);
        log.info("Create book response ==> {}", book);

        return bookMapper.convertBookToCreateResponse(book);
    }

    @Override
    public CreateOrUpdateBookResponse updateBook(Long id, CreateOrUpdateBookRequest request) {
        log.info("Update book request ==> {}", request.toString());
        validateCreateOrUpdate(request);

        final Book book = bookRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        bookRepository.save(book
                .setName(request.getName())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice()));

        log.info("Update book response ==> {}", book);

        return bookMapper.convertBookToCreateResponse(book);
    }

    @Override
    public void updateQuantity(Long id, int quantity) {
        final Book book = bookRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        bookRepository.save(book
                .setUpdatedAt(new Date())
                .setQuantity(quantity));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void softDeleteBook(Long id) {
        final Book book = bookRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        book.setDeletedAt(new Date());

        bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        final Book book = bookRepository.findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        return book;
    }

    private void validateCreateOrUpdate(CreateOrUpdateBookRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Invalid Book Name");
        }
    }
}
