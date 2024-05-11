package com.library.management.system.model.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatronDto implements Serializable {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
}
