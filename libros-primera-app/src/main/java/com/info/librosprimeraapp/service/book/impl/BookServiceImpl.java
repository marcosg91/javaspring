/// BookServiceImpl.java

package com.info.librosprimeraapp.service.book.impl;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.mapper.author.AuthorMapper;
import com.info.librosprimeraapp.mapper.book.BookMapper;
import com.info.librosprimeraapp.model.dto.book.BookDTO;
import com.info.librosprimeraapp.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    Map<UUID,Book> bookMap;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {

        this.bookMapper = bookMapper;

        bookMap = new HashMap<>();

        Book book = new Book();
        book.setUuid(UUID.randomUUID());
        //book.setAuthor("Gabriel Garcia Marquez");
        book.setTitle("Cien años de soledad");

        Book book2 = new Book();
        book2.setUuid(UUID.randomUUID());
        //book2.setAuthor("George Orwell");
        book2.setTitle("1984");

        Book book3 = new Book();
        book3.setUuid(UUID.randomUUID());
        //book3.setAuthor("Antoine de Saint-Exupery");
        book3.setTitle("El principito");

        bookMap.put(book.getUuid(),book);
        bookMap.put(book2.getUuid(),book2);
        bookMap.put(book3.getUuid(),book3);
    }

    @Override
    public List<BookDTO> getAllBooks() {

        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book:bookMap.values()) {
            bookDTOS.add(bookMapper.bookToBookDTO(book));
        }

        return bookDTOS;
    }

    @Override
    public Book createBook(BookDTO book) {
        Book newBook = bookMapper.bookDTOtoBook(book);
        //Queda pendiente agregar autores
        bookMap.put(newBook.getUuid(),newBook);
        return newBook;
    }

    @Override
    public Optional<BookDTO> updateBook(UUID uuidBook, BookDTO bookUpdated) {
        //Buscamos libro
        Book book = bookMap.get(uuidBook);

        if(book != null){
            updatingBook(book,bookUpdated);
            return Optional.of(bookMapper.bookToBookDTO(book));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteBook(UUID uuidBook) {
        return false;
    }

    @Override
    public Optional<BookDTO> getBookById(UUID uuid) {

        return Optional.of(
                bookMapper.bookToBookDTO( bookMap.get(uuid) )
        );
    }

    private void updatingBook(Book book,BookDTO bookUpdated){

        if (!bookUpdated.getTitle().isBlank()){
            book.setTitle(bookUpdated.getTitle());
        }

        if(bookUpdated.getNumberPages() > 0){
            book.setNumberPages(bookUpdated.getNumberPages());
        }

        if (!bookUpdated.getIsbn().isBlank()){
            book.setIsbn(bookUpdated.getIsbn());
        }

    }

    private boolean deleteBookByName(String title) {
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookMap.values().remove(book);
                return true;
            }
        }
        return false;
    }
}