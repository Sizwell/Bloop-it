package com.itech.clientrequestservices.contoller;

import com.itech.clientrequestservices.service.ClientRequestService;
import com.itech.clientrequestservices.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2")
@Slf4j
public class ClientRequestController {

    private final WebClient.Builder webClientBuilder;
    ClientRequestService clientRequestService;

    private final TestService testService;

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
