package com.itech.clientrequestservices.service;

import com.itech.clientrequestservices.dto.WordDto;
import com.itech.clientrequestservices.model.WordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ClientRequestService {

    private final WebClient webClient;
    private final ReactiveCircuitBreaker reactiveCircuitBreaker;

    public ClientRequestService(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        //method does not work without localhost:8080/ -> http://sensitive-word-service
        this.webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080/sensitive-word-service")
                .build();
        this.reactiveCircuitBreaker = circuitBreakerFactory.create("user-service");
    }

    public Mono<String> processUserInput (String userInput)
    {
        String url = "/api/v2/sensitiveWords/process";
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam("word", userInput)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userInput), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }
    
}
