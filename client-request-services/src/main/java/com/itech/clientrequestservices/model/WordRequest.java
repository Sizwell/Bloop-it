package com.itech.clientrequestservices.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WordRequest {
    @Id
    private Long id;
    private String word;
}
