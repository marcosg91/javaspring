//BookService.java

package com.info.librosprimeraapp.service.book;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.exceptions.NotFoundException;
import com.info.librosprimeraapp.model.dto.book.BookDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<BookDTO> getAllBooks();

    Book createBook(BookDTO book) throws NotFoundException;

    Optional<BookDTO> updateBook(UUID uuidBook, BookDTO bookUpdated);

    boolean deleteBook(UUID uuidBook);

    Optional<BookDTO> getBookById(UUID uuid);
}