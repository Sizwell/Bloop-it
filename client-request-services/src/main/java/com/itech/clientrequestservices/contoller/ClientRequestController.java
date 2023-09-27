package com.itech.clientrequestservices.contoller;

import com.itech.clientrequestservices.dto.WordDto;
import com.itech.clientrequestservices.service.ClientRequestService;
import com.itech.clientrequestservices.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2/sensitiveWords")
@Slf4j
public class ClientRequestController {

    private final WebClient.Builder webClientBuilder;
    ClientRequestService clientRequestService;

    private final TestService testService;

//    @PostMapping("/bloop")
//    public List<String> getWords(@Validated @RequestParam String userInput)
//    {
//        log.info("Testing 1 {}", userInput.toUpperCase());
//
//        return testService.testResponse(userInput);
//    }

    @GetMapping("/blooped")
    public Mono<String> getBlooped()
    {
        String sensitiveWordsServiceUrl = "http://sensitive-word-service/api/v2/sensitiveWords/words";

        return webClientBuilder.build()
                .get()
                .uri(sensitiveWordsServiceUrl)
                .retrieve()
                .bodyToMono(String.class);
    }
}
