//BookServiceImpl.java: no se conecta a una base de datos

package com.info.librosprimeraapp.service.book.impl;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.service.book.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("BookServicePrincipal")
public class BookServiceImpl implements BookService {
    Map<UUID, Book> bookMap;

    public BookServiceImpl() {
        Book book = new Book();
        book.setUuid(UUID.randomUUID());
        book.setAuthor("Gabriel Garcia Marquez");
        book.setTitle("Cien años de soledad");

        bookMap = new HashMap<>();
        Book book2 = new Book();
        book2.setUuid(UUID.randomUUID());
        book2.setAuthor("Antoine de Saint-Exupery");
        book2.setTitle("El principito");

        bookMap.put(book.getUuid(), book);
        bookMap.put(book2.getUuid(), book2);
    }

    @Override
    public List<Book> getAllBooks() {

        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book createBook(Book book) {
        book.setUuid(UUID.randomUUID());
        bookMap.put(book.getUuid(), book);
        return book;
    }

    // agregar implementación para el método findBookByTitle()
    @Override
    public Optional<Book> findBookByTitle(String title) {
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return Optional.of(book);
            }
        }
        return null;
    }

    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //buscamos libro
        Book book = bookMap.get(uuidBook);
        if (book != null) {
            updatingBook(book, bookUpdated);
            return Optional.of(book);
        } else {
            return null;
        }
    }

    private void updatingBook(Book book, Book bookUpdated) {
        if (bookUpdated.getTitle() != null) {
            book.setTitle(bookUpdated.getTitle());
        }
        if (bookUpdated.getAuthor() != null) {
            book.setAuthor(bookUpdated.getAuthor());
        }
    }
    @Override
    public boolean deleteBookByName(String title) {
        Book bookToDelete = null;
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookToDelete = book;
                break;
            }
        }
        return (bookToDelete != null) ? bookMap.remove(bookToDelete.getUuid()) != null : false;
    }

    @Override
    public Optional<Book> getBookById(UUID uuid) {
        return Optional.of(bookMap.get(uuid));
    }
}
