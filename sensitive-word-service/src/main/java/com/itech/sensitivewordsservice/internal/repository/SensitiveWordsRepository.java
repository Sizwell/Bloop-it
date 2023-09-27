package com.itech.sensitivewordsservice.internal.repository;

import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SensitiveWordsRepository extends JpaRepository <SensitiveWords, Long>{
    Optional<SensitiveWords> findByWord(String word);

    @Query("SELECT sw.word FROM SensitiveWords sw WHERE sw.id = :id")
    Optional<SensitiveWords> findSensitiveWordsById(Long id);
}
