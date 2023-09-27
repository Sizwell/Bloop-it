package com.itech.clientrequestservices.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TestService {

    public List<String> testResponse(String input)
    {
        List<String> output = new ArrayList<>();
        log.info("Testing {}", input);
        return output;
    }
}
