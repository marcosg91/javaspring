package com.info.librosprimeraapp.service;

import com.info.librosprimeraapp.domain.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author createAuthor(Author author);

    Optional<Author> findAuthorById(UUID authorId);

    Optional<Author> findAuthorByNombreAndApellido(String nombre, String apellido);

    boolean deleteAuthor(UUID authorId);
}
