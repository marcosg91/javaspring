// BookServiceJPAImpl.java

package com.info.librosprimeraapp.service.book.impl;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.exceptions.NotFoundException;
import com.info.librosprimeraapp.mapper.book.BookMapper;
import com.info.librosprimeraapp.model.dto.book.BookDTO;
import com.info.librosprimeraapp.repository.author.AuthorRepository;
import com.info.librosprimeraapp.repository.book.BookRepository;
import com.info.librosprimeraapp.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class BookServiceJPAImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;


    @Override
    public List<BookDTO> getAllBooks() {

        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book:bookRepository.findAll()) {
            bookDTOS.add(bookMapper.bookToBookDTO(book));
        }

        return bookDTOS;
    }

    @Override
    public Book createBook(BookDTO book) throws NotFoundException {

        Book newBook = bookMapper.bookDTOtoBook(book);

        Optional<Author> author = authorRepository.findById(UUID.fromString(book.getIdAuthor()));

        if (author.isPresent()){
            newBook.setAuthor(author.get());
            return bookRepository.save(newBook);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Optional<BookDTO> updateBook(UUID uuidBook, BookDTO bookUpdated) {
        //Buscar libro por ID
        Optional<Book> bookOptional = bookRepository.findById(uuidBook);

        if(bookOptional.isPresent()){
            updatingBook(bookOptional.get(),bookUpdated);
            //Save --> Si existe entonces lo actualiza y sino lo crea.
            bookRepository.saveAndFlush(bookOptional.get());
            return Optional.of(bookMapper.bookToBookDTO(bookOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    private void updatingBook(Book book,BookDTO bookUpdated){

        if (!bookUpdated.getTitle().isBlank()){
            book.setTitle(bookUpdated.getTitle());
        }

        if (!bookUpdated.getIdAuthor().isBlank()){
            Optional<Author> author = authorRepository.findById(UUID.fromString(bookUpdated.getIdAuthor()));

            if (author.isPresent()){
                book.setAuthor(author.get());
            }
        }

        if(bookUpdated.getNumberPages() > 0){
            book.setNumberPages(bookUpdated.getNumberPages());
        }

        if (!bookUpdated.getIsbn().isBlank()){
            book.setIsbn(bookUpdated.getIsbn());
        }

    }

    @Override
    public boolean deleteBook(UUID uuidBook) {
        if (bookRepository.existsById(uuidBook)){
            bookRepository.deleteById(uuidBook);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BookDTO> getBookById(UUID uuid) {
        Optional<Book> bookOptional = bookRepository.findById(uuid);

        if (bookOptional.isPresent()){
            return Optional.of(bookMapper.bookToBookDTO(bookOptional.get()));
        }
        return Optional.empty();
    }

}