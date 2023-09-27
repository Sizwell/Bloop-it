package com.itech.sensitivewordsservice.internal.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class SensitiveWords {
    @Id
    @SequenceGenerator(
            name = "secrete_words",
            sequenceName = "secrete_words",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "secrete_words"
    )

    private Long id;
    private String word;

}
