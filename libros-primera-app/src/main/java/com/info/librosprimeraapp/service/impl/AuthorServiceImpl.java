// AuthorServiceImpl.java
package com.info.librosprimeraapp.service.impl;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.repository.author.AuthorRepository;
import com.info.librosprimeraapp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        author.setId(UUID.randomUUID());
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> findAuthorById(UUID authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public Optional<Author> findAuthorByNombreAndApellido(String nombre, String apellido) {
        return authorRepository.findByNombreAndApellido(nombre, apellido);
    }

    @Override
    public Optional<Author> updateAuthor(UUID authorId, Author author) {
        return authorRepository.findById(authorId).map(existingAuthor -> {
            existingAuthor.setNombre(author.getNombre());
            existingAuthor.setApellido(author.getApellido());
            existingAuthor.setFechaDeNacimiento(author.getFechaDeNacimiento());
            return authorRepository.save(existingAuthor);
        });
    }

    @Override
    public boolean deleteAuthor(UUID authorId) {
        return authorRepository.findById(authorId).map(author -> {
            authorRepository.delete(author);
            return true;
        }).orElse(false);
    }
}
