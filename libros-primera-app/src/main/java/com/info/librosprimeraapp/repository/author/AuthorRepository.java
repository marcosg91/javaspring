// AuthorRepository.java
package com.info.librosprimeraapp.repository.author;

import com.info.librosprimeraapp.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByNombreAndApellido(String nombre, String apellido);

    default Optional<Author> findByFechaDeNacimiento() {
        return findByFechaDeNacimiento(null);
    }

    Optional<Author> findByFechaDeNacimiento(LocalDate fechaDeNacimiento);
}

