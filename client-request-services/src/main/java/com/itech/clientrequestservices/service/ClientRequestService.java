package com.itech.clientrequestservices.service;


import com.itech.clientrequestservices.dto.WordDto;
import com.itech.clientrequestservices.model.WordRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
//@RequiredArgsConstructor
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

//    private final WebClient.Builder webClientBuilder;
//
//    public Mono<String> getBlooped() {
//        String sensitiveWordsServiceUrl = "http://sensitive-word-service/api/v2/sensitiveWords/words";
//
//        return webClientBuilder.build()
//                .get()
//                .uri(sensitiveWordsServiceUrl)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//    private final WebClient webClient;
//
//    public ClientRequestService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://sensitive-word-service").build();
//    }
//
//    @CircuitBreaker(name = "microserviceCircuitBreaker", fallbackMethod = "fallback")
//    public Mono<String> getBlooped() {
//        return webClient.get()
//                .uri("/api/v2/sensitiveWords/words")
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//    public Mono<String> fallback(Exception ex) {
//        return Mono.just("Fallback response" + ex);
//    }

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

    public Mono<String> getBlooped() {
        return reactiveCircuitBreaker
                .run(webClient
                        .get()
                        .uri("/api/v2/sensitiveWords/words")
                        .retrieve()
                        .bodyToMono(String.class), throwable -> {
                    log.warn("Error making request to Sensitive Words Service");

                    return Mono.just("Could not connect to Sensitive Words Service. Please try again later!");
                });
    }


}
