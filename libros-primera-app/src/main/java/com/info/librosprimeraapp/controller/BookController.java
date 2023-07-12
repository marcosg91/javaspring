//BookController.java: se comunica con el Service

package com.info.librosprimeraapp.controller;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public List<Book> getAllBooks() {
        log.info("consultar todos los libros");
        return bookService.getAllBooks();
    }

    // post---> crear un libro
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        log.info("nuevo libro");
        return bookService.createBook(book);
    }

    // get---> obtener libro por título
    @GetMapping("/title/{title}")
    public ResponseEntity<?> findBookByTitle(@PathVariable("title") String title) {
        Book book = bookService.findBookByTitle(title);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("libro no encontrado");
        }
    }

    // put---> actualizar un recurso
    @PutMapping("/update/{idBook}")
    public String updateBook(@PathVariable(value = "idBook") UUID idBook, @RequestBody Book bookUpdated) {
        Optional<Book> book = bookService.updateBook(idBook, bookUpdated);

        if (!book.isPresent()) {
            log.info("libro no encontrado");
            return "libro no encontrado";
        } else {
            log.info("libro actualizado");
            return "/api/v1/book/" + book.get().getUuid();
        }
    }

    // delete---> eliminar un recurso
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<String> deleteBook(@PathVariable("title") String title) {
        boolean deleted = bookService.deleteBookByName(title);
        if (deleted) {
            log.info("libro eliminado");
            return ResponseEntity.ok("libro eliminado correctamente");
        } else {
            log.info("libro no encontrado");
            return ResponseEntity.notFound().build();
        }
    }
}
