package com.itech.sensitivewordsservice.internal.service;

import com.itech.sensitivewordsservice.internal.dto.WordRequest;
import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddWordService {

    private final SensitiveWordsRepository sensitiveWordsRepository;

    public SensitiveWords addNewWord(WordRequest wordRequest)
    {
        SensitiveWords sensitiveWords = SensitiveWords.builder()
                .word(wordRequest.getWord().toUpperCase())
                .build();
        sensitiveWordsRepository.save(sensitiveWords);
        log.info("Word with ID {} saved to DB", sensitiveWords.getId());
        return sensitiveWords;
    }
}
