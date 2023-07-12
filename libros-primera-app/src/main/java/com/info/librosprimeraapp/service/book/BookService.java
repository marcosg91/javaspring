package com.info.librosprimeraapp.service.book;

import com.info.librosprimeraapp.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBooks();

    Book createBook(Book book);

    Optional<Book> findBookByTitle(String title);

    Optional<Book> updateBook(UUID uuidBook, Book bookUpdated);

    boolean deleteBookByName(String title);

    Optional<Book> getBookById(UUID uuid);
}
