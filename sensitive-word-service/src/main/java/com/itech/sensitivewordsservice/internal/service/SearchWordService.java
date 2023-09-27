package com.itech.sensitivewordsservice.internal.service;

import com.itech.sensitivewordsservice.internal.dto.WordResponse;
import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchWordService {

    private final SensitiveWordsRepository sensitiveWordsRepository;

    Optional<SensitiveWords> sensitiveWordsOptional;

    public List<WordResponse> responses(String word) {
        sensitiveWordsOptional = sensitiveWordsRepository.findByWord(word);
        if (sensitiveWordsOptional.isEmpty()) {
            log.warn("Word not found!");
        }

        return sensitiveWordsOptional.stream().map(this::mapToWordResponse).toList();

    }

    private WordResponse mapToWordResponse(SensitiveWords sensitiveWords) {
        return WordResponse.builder()
                .id(sensitiveWords.getId())
                .word(sensitiveWords.getWord())
                .build();
    }
}
