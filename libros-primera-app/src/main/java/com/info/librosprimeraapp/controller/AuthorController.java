package com.info.librosprimeraapp.controller;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    @Qualifier("AuthorServicePrincipal")
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // POST - Crear un autor
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    // DELETE - Eliminar un autor por su ID
    @DeleteMapping("/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable UUID authorId) {
        boolean deleted = authorService.deleteAuthor(authorId);
        if (deleted) {
            return ResponseEntity.ok("Autor eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor no encontrado");
        }
    }

    // GET - Buscar un autor por su ID
    @GetMapping("/{authorId}")
    public ResponseEntity<?> findAuthorById(@PathVariable UUID authorId) {
        Optional<Author> author = authorService.findAuthorById(authorId);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor no encontrado");
        }
    }

    // GET - Buscar un autor por nombre y apellido
    @GetMapping("/search")
    public ResponseEntity<?> findAuthorByNombreAndApellido(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido) {
        Optional<Author> author = authorService.findAuthorByNombreAndApellido(nombre, apellido);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor no encontrado");
        }
    }

    // GET - Obtener todos los autores
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
