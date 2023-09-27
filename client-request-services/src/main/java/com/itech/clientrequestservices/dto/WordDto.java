package com.itech.clientrequestservices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WordDto {

    @NotBlank(message = "Cannot store an empty String - Please provide a Valid Word")
    private String word;
}
