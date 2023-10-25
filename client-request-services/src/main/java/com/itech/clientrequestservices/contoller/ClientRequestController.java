package com.itech.clientrequestservices.contoller;

import com.itech.clientrequestservices.service.ClientRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2")
@Slf4j
public class ClientRequestController {

    private final ClientRequestService clientRequestService;

    @PostMapping(value = "/bloop", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> processUserInput (@Valid @RequestParam ("word") String userInput) {
        return clientRequestService.processUserInput(userInput);
    }
}
