package com.library.management.system.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;

}
