//Book.java

package com.info.librosprimeraapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 100,columnDefinition = "varchar(100)",updatable = true,nullable = false)
    private String title;

    @ManyToOne
    private Author author;

    @Column(unique = true)
    private String isbn;

    private int numberPages;

    public void setAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberPages=" + numberPages +
                '}';
    }
}
