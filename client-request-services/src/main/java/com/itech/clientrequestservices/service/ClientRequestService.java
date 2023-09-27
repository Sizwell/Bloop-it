package com.itech.clientrequestservices.service;


import com.itech.clientrequestservices.dto.WordDto;
import com.itech.clientrequestservices.model.WordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientRequestService {


    public String userRequest(String userInput)
    {
        log.info("Checking {} ", userInput);
        return "Processed 1 " + userInput.toLowerCase();
    }

    private WordDto mapToWordResponse(WordRequest wordRequest)
    {
        return WordDto.builder()
                .word(wordRequest.getWord())
                .build();
    }

    private final WebClient.Builder webClientBuilder;



    public Mono<String> getData() {
        return webClientBuilder.baseUrl("http://blooper-service")
                .build()
                .get()
                .uri("/words")
                .retrieve()
                .bodyToMono(String.class);
    }

}
