package com.info.librosprimeraapp.repository.book;

import com.info.librosprimeraapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface BookRepository extends JpaRepository<Book, UUID> {
    //query method
    Optional<Book> findBookByTitleEqualsIgnoreCase(String title);
}
