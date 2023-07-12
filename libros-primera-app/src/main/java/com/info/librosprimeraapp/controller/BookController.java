//BookController.java: se comunica con el Service

package com.info.librosprimeraapp.controller;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.exceptions.NotFoundException;
import com.info.librosprimeraapp.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //anotacion a nivel de clase
@RequestMapping("/api/v1/book")
@Slf4j

public class BookController {
    // IoC inversion de control
    @Qualifier("BookServicePrincipal")
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // get---> obtener todos los libros
    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false, name = "nameBook") String nameBook) {
        log.info("consultar todos los libros");
        return bookService.getAllBooks();
    }

    // post---> crear un libro
    @PostMapping
    public ResponseEntity createBook(@RequestBody Book book) {
        log.info("creacion de nuevo libro");
        Book bookCreated = bookService.createBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/v1/book/" + bookCreated.getUuid());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    // get---> obtener libro por título
    @GetMapping("/title/{title}")
    public ResponseEntity<?> findBookByTitle(@PathVariable("title") String title) {
        Optional<Book> book = bookService.findBookByTitle(title);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("libro no encontrado");
        }
    }

    // put---> actualizar un recurso
    @PutMapping("/update/{idBook}")
    public ResponseEntity updateBook(@PathVariable(value = "idBook") UUID idBook, @RequestBody Book bookUpdated)
            throws NotFoundException {
        Optional<Book> book = bookService.updateBook(idBook, bookUpdated);

        if (!book.isPresent()) {
            log.warn("libro no encontrado");
            throw new NotFoundException();
        } else {
            log.info("libro actualizado");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // delete---> eliminar un recurso
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<String> deleteBook(@PathVariable("title") String title)
            throws NotFoundException {
        boolean deleted = bookService.deleteBookByName(title);

        if (deleted) {
            log.warn("libro eliminado");
            throw new NotFoundException();
        } else {
            log.info("libro no encontrado");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // por variable ---> informacion en URL
    // por parametro (param) ---> parametro de la request

    @GetMapping("/{idBook}")
    public Book getBookById(@PathVariable(value = "idBook") UUID idBook) throws NotFoundException {
        return bookService.getBookById(idBook).orElseThrow(NotFoundException::new);
    }
}
