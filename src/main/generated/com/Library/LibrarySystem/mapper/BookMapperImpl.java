package com.Library.LibrarySystem.mapper;

import com.Library.LibrarySystem.domain.entity.Book;
import com.Library.LibrarySystem.dto.Book.BookDTO;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-25T09:53:20+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public CreateOrUpdateBookResponse convertBookToCreateResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        CreateOrUpdateBookResponse createOrUpdateBookResponse = new CreateOrUpdateBookResponse();

        createOrUpdateBookResponse.setCreatedAt( book.getCreatedAt() );
        createOrUpdateBookResponse.setName( book.getName() );
        createOrUpdateBookResponse.setQuantity( book.getQuantity() );
        createOrUpdateBookResponse.setPrice( book.getPrice() );

        return createOrUpdateBookResponse;
    }

    @Override
    public BookDTO convertBookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setCreatedAt( book.getCreatedAt() );
        bookDTO.setName( book.getName() );
        bookDTO.setQuantity( book.getQuantity() );
        bookDTO.setPrice( book.getPrice() );

        return bookDTO;
    }
}
