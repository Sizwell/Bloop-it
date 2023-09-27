package com.itech.sensitivewordsservice.internal.controller;


import com.itech.sensitivewordsservice.exceptions.WordNotFoundException;
import com.itech.sensitivewordsservice.internal.dto.WordRequest;
import com.itech.sensitivewordsservice.internal.dto.WordResponse;
import com.itech.sensitivewordsservice.internal.entity.SensitiveWords;
import com.itech.sensitivewordsservice.internal.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/sensitiveWords")
@RequiredArgsConstructor
@Slf4j
public class SensitiveWordsController {

    private final SensitiveWordsService sensitiveWordsService;
    private final AddWordService addWordService;
    private final UploadWordsService uploadWordsService;
    private final SearchWordService searchWordService;
    private final UpdateWordsService updateWordsService;
    private final DeleteWordService deleteWordService;

    @PostMapping("/word")
    public ResponseEntity<SensitiveWords> addWord(@Valid @RequestBody WordRequest wordRequest) {
        SensitiveWords words = addWordService.addNewWord(wordRequest);
        return new ResponseEntity<>(words, HttpStatus.OK);

    }

    @GetMapping("/words")
    public ResponseEntity<List<WordResponse>> getAllWords() {
        List<WordResponse> words = sensitiveWordsService.getAllSensitiveWords();
        log.info("Sensitive Words List: {}",words);
        return ResponseEntity.ok(words);
    }

    @PostMapping("/words")
    public ResponseEntity<String> saveFromFile() {

        String filePath = "sensitive-word-service/src/main/resources/data/sql_sensitive_list.txt";
        File file = new File(filePath);

        if (file.exists() && file.isFile())
        {
            try {
                uploadWordsService.processFile(filePath);
                return new ResponseEntity<>(HttpStatus.OK);

            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error occurred while uploading words \nReason: " + e.getMessage());
            }
        } else {
            log.error("Error occurred while processing the file.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred while processing the file.");
        }
    }

    @GetMapping("/word/search")
    public ResponseEntity<List<WordResponse>> returnOneWord(@RequestParam("word") String searchWord) {
        List<WordResponse> words = searchWordService.responses(searchWord.toUpperCase());
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @PutMapping("/word/{id}")
    public ResponseEntity<SensitiveWords> updateWord(@PathVariable Long id, @RequestBody WordRequest wordRequest)
    {
        try {
            SensitiveWords update = updateWordsService.updateWord(id, wordRequest);
            log.info("Word with id '{}' has been successfully Updated to '{}'", id, update.getWord());
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (WordNotFoundException wnf) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/word/{id}")
    public ResponseEntity<SensitiveWords> deleteWordById(@PathVariable("id") Long id)
    {
        try {
            deleteWordService.deleteWord(id);
            log.info("Word with ID {} has been deleted", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (WordNotFoundException wnf) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
