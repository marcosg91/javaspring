package com.info.librosprimeraapp.service.impl;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("AuthorServicePrincipal")
public class AuthorServiceImpl implements AuthorService {
    Map<UUID, Author> authorMap;

    public AuthorServiceImpl() {
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setNombre("Gabriel");
        author.setApellido("Garcia Marquez");

        authorMap = new HashMap<>();
        Author author2 = new Author();
        author2.setId(UUID.randomUUID());
        author2.setNombre("Antoine");
        author2.setApellido("de Saint-Exupery");

        authorMap.put(author.getId(), author);
        authorMap.put(author2.getId(), author2);
    }

    @Override
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authorMap.values());
    }

    @Override
    public Author createAuthor(Author author) {
        author.setId(UUID.randomUUID());
        authorMap.put(author.getId(), author);
        return author;
    }

    @Override
    public Optional<Author> findAuthorById(UUID authorId) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> findAuthorByNombreAndApellido(String nombre, String apellido) {
        return Optional.empty();
    }

    // Agregar implementación para el método findAuthorByNombreAndApellido()
    @Override
    public Optional<Author> findAuthorByNombreAndApellido(String fullName) {
        for (Author author : authorMap.values()) {
            String fullAuthorName = author.getNombre() + " " + author.getApellido();
            if (fullAuthorName.equalsIgnoreCase(fullName)) {
                return Optional.of(author);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> updateAuthor(UUID authorId, Author authorUpdated) {
        Author author = authorMap.get(authorId);
        if (author != null) {
            updateAuthorFields(author, authorUpdated);
            return Optional.of(author);
        } else {
            return Optional.empty();
        }
    }

    private void updateAuthorFields(Author author, Author authorUpdated) {
        if (authorUpdated.getNombre() != null) {
            author.setNombre(authorUpdated.getNombre());
        }
        if (authorUpdated.getApellido() != null) {
            author.setApellido(authorUpdated.getApellido());
        }
    }

    @Override
    public boolean deleteAuthor(UUID authorId) {
        return authorMap.remove(authorId) != null;
    }
}
