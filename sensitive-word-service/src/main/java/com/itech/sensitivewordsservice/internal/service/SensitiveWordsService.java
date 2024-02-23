package com.itech.sensitivewordsservice.internal.service;

import com.itech.sensitivewordsservice.internal.dto.WordRequest;
import com.itech.sensitivewordsservice.internal.dto.WordResponse;
import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.datatransfer.FlavorListener;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensitiveWordsService {

    private final SensitiveWordsRepository sensitiveWordsRepository;

    public List<WordResponse> getAllSensitiveWords() {
        List<SensitiveWords> words = sensitiveWordsRepository.findAll();

        return words.stream().map(this::mapToWordResponse).toList();
    }

    private WordResponse mapToWordResponse(SensitiveWords sensitiveWords) {
        return WordResponse.builder()
                .id(sensitiveWords.getId())
                .word(sensitiveWords.getWord())
                .build();
    }

    public String bloopIt(String userInput) {
        List<SensitiveWords> sensitiveWords = sensitiveWordsRepository.findAll();
        for (SensitiveWords words : sensitiveWords) {
            String sensitiveWord = words.getWord();
            userInput = userInput.replaceAll(sensitiveWord, "*".repeat(sensitiveWord.length()));
        }
        return userInput;
    }

}
