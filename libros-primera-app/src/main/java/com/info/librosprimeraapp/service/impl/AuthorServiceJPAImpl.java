// AuthorServiceJPAImpl.java
package com.info.librosprimeraapp.service.impl;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.repository.author.AuthorRepository;
import com.info.librosprimeraapp.service.AuthorService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
public class AuthorServiceJPAImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceJPAImpl(AuthorRepository authorRepository) {
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
    public Optional<Author> findAuthorByNombreAndApellido(String fullName) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> updateAuthor(UUID authorId, Author authorUpdated) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAuthor(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
            return true;
        }
        return false;
    }
}
