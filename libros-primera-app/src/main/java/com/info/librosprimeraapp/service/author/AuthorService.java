// AuthorService.java
package com.info.librosprimeraapp.service.author;

import com.info.librosprimeraapp.domain.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author createAuthor(Author author);

    Optional<Author> findAuthorById(UUID authorId);

    Optional<Author> findAuthorByNombreAndApellido(String nombre, String apellido);

    Optional<Author> findAuthorByNombreAndApellido(String fullName);

    Optional<Author> updateAuthor(UUID authorId, Author author);

    boolean deleteAuthor(UUID authorId);
}
