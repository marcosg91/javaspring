//BookMapperImpl.java

package com.info.librosprimeraapp.mapper.book.impl;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.mapper.book.BookMapper;
import com.info.librosprimeraapp.model.dto.book.BookDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public Book bookDTOtoBook(BookDTO bookDTO) {
        return Book.builder()
                .uuid(UUID.randomUUID())
                .isbn(bookDTO.getIsbn())
                .title(bookDTO.getTitle())
                .numberPages(bookDTO.getNumberPages())
                .build();
    }

    @Override
    public BookDTO bookToBookDTO(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .numberPages(book.getNumberPages())
                .idAuthor(book.getAuthor().getUuid().toString())
                .build();
    }
}