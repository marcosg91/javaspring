package com.info.librosprimeraapp.controller;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
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
        return bookService.getAllBooks();
    }

    // post---> crear un libro
    @PostMapping
    public Book createBook(@RequestBody Book book) {
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
            System.out.println("libro no encontrado");
            return "libro no encontrado";
        } else {
            System.out.println("libro actualizado");
            return "/api/v1/book/" + book.get().getUuid();
        }
    }

    // delete---> eliminar un recurso
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<String> deleteBook(@PathVariable("title") String title) {
        boolean deleted = bookService.deleteBookByName(title);
        if (deleted) {
            return ResponseEntity.ok("libro eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}