package com.Library.LibrarySystem.mapper;

import com.Library.LibrarySystem.domain.entity.Book;
import com.Library.LibrarySystem.dto.Book.BookDTO;
import com.Library.LibrarySystem.dto.Book.CreateOrUpdateBookResponse;
import org.mapstruct.Mapper;

import java.util.Formatter;

@Mapper(componentModel = "spring", uses = {Formatter.class})
public interface BookMapper {
    CreateOrUpdateBookResponse convertBookToCreateResponse(Book book);

    BookDTO convertBookToBookDTO(Book book);
}
