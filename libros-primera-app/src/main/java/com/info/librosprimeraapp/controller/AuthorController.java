// AuthorController.java
package com.info.librosprimeraapp.controller;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.service.author.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> findAuthorById(@PathVariable UUID authorId) {
        Optional<Author> author = authorService.findAuthorById(authorId);
        return author.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable UUID authorId, @RequestBody Author author) {
        Optional<Author> updatedAuthor = authorService.updateAuthor(authorId, author);
        return updatedAuthor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable UUID authorId) {
        boolean deleted = authorService.deleteAuthor(authorId);
        if (deleted) {
            return ResponseEntity.ok("Author deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
    }
}
