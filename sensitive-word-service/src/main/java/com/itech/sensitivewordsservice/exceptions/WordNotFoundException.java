package com.itech.sensitivewordsservice.exceptions;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(Long id) {
        super("Word with ID " + "'" + id + "'" + " does not exist.");
    }
}
