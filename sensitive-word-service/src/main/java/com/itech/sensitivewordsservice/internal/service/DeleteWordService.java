package com.itech.sensitivewordsservice.internal.service;

import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.exceptions.WordNotFoundException;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class DeleteWordService {
    private final SensitiveWordsRepository sensitiveWordsRepository;

    public void deleteWord(Long id)
    {
        Optional<SensitiveWords> exists = sensitiveWordsRepository.findSensitiveWordsById(id);
        if (exists.isPresent())
        {
            sensitiveWordsRepository.deleteById(id);
        } else {
            throw new WordNotFoundException(id);
        }
    }
}
