//BookMapper.java

package com.info.librosprimeraapp.mapper.book;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.model.dto.book.BookDTO;

public interface BookMapper {

    Book bookDTOtoBook(BookDTO bookDTO);

    BookDTO bookToBookDTO(Book book);
}