// AuthorService.java

package com.info.librosprimeraapp.service.author;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.model.dto.author.AuthorDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {

    Author createAuthor(AuthorDTO author);

    boolean deleteAuthor(UUID uuid);

    Optional<Author> updateAuthor(UUID uuid, AuthorDTO authorUpdated);

    Optional<AuthorDTO> getAuthorById(UUID uuid);

    Optional<AuthorDTO> getAuthorByNameAndSurname(String name, String surname);

    List<AuthorDTO> getAllAuthors();

}