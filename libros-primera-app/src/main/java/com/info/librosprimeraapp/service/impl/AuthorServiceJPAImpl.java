package com.info.librosprimeraapp.service.impl;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.repository.author.AuthorRepository;
import com.info.librosprimeraapp.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorServiceJPAImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        author.setId(UUID.randomUUID());
        return authorRepository.save(author);
    }

    public boolean deleteAuthor(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
            return true;
        }
        return false;
    }

    public Optional<Author> findAuthorById(UUID authorId) {
        return authorRepository.findById(authorId);
    }

    public Optional<Author> findAuthorByNombreAndApellido(String nombre, String apellido) {
        return authorRepository.findByNombreAndApellido(nombre, apellido);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
