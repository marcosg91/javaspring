package com.info.librosprimeraapp.mapper.author;

import com.info.librosprimeraapp.domain.Author;
import com.info.librosprimeraapp.model.dto.author.AuthorDTO;

public interface AuthorMapper {
    Author authorDtoToAuthor(AuthorDTO author);

    AuthorDTO authorToAuthorDto(Author author);
}