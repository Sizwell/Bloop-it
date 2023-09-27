package com.itech.sensitivewordsservice.internal.service;

import com.itech.sensitivewordsservice.internal.dto.WordRequest;
import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.internal.repository.SensitiveWordsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateWordsService {

    private final SensitiveWordsRepository sensitiveWordsRepository;
    @Transactional
    public SensitiveWords updateWord(Long id, WordRequest wordRequest)
    {
        SensitiveWords sensitiveWords;
        sensitiveWords = sensitiveWordsRepository
                .findById(id)
                .orElseThrow(()-> new IllegalStateException("Word with ID " + "'" +  id + "'" + " does not exist"));

        //Check if the search word is not null, word has characters and is not equal to word already in DB
        if (wordRequest.getWord().length() > 0 && !Objects.equals(sensitiveWords.getWord(), wordRequest.getWord()))
        {
            //remove any white spaces (if any) in the new String then update
            String updateString = wordRequest.getWord().replaceAll("\\s+", "").toUpperCase();
            sensitiveWords.setWord(updateString);
            sensitiveWordsRepository.save(sensitiveWords);
        }
        return sensitiveWords;
    }
}
