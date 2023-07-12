package com.info.librosprimeraapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Builder //se encarga de la construccion del objeto, muy importante en spring

public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator" )
    @JdbcTypeCode((SqlTypes.CHAR))

    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID uuid;

    @Column(length = 100, columnDefinition = "varchar(100)", updatable = true, nullable = false)
    private String title;

    @Column(length = 100, columnDefinition = "varchar(100)",updatable = true, nullable = false)
    private String author;

    @Column(unique = true) //para que no pueda haber libros con el mismo isbn
    private String isbn;

    private int numberPage;

    //devuelve en pantalla el objeto construido
    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberPage=" + numberPage +
                '}';
    }
}

