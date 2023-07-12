//BookServiceJPAImpl.java: se conecta a una base de datos = mysql

package com.info.librosprimeraapp.service.book.impl;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.repository.book.BookRepository;
import com.info.librosprimeraapp.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor

public class BookServiceJPAImpl implements BookService {


    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();//traer todos los libros
    }

    @Override
    public Book createBook(Book book) {
        book.setUuid(UUID.randomUUID());
        return bookRepository.save(book);//guardar en base de datos
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitleEqualsIgnoreCase(title);
    }

    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //buscar libro por id
        Optional<Book> bookOptional = bookRepository.findById(uuidBook);

        if (bookOptional.isPresent()) {
            updatingBook(bookOptional.get(), bookUpdated);
            //save ----> si existe entonces lo actualiza y si no existe lo crea
            return Optional.of(bookRepository.save(bookOptional.get()));
        } else {
            return Optional.empty();
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


    public boolean deleteBookByName(String title) {
        Optional<Book> bookOptional = bookRepository.findBookByTitleEqualsIgnoreCase(title);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Book> getBookById(UUID uuid) {

        return Optional.of(bookRepository.findById(uuid)).orElse(null);
    }
}
