//BookDTO.java

package com.info.librosprimeraapp.model.dto.book;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookDTO {
    private String title;
    private String isbn;
    private int numberPages;
    private String idAuthor;
}