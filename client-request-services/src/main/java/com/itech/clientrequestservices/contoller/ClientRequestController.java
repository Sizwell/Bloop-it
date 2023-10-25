package com.itech.clientrequestservices.contoller;

import com.itech.clientrequestservices.dto.WordDto;
import com.itech.clientrequestservices.model.WordRequest;
import com.itech.clientrequestservices.service.ClientRequestService;
import com.itech.clientrequestservices.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2")
@Slf4j
public class ClientRequestController {

    private final ClientRequestService clientRequestService;

//    @PostMapping(value = "/blooped", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Mono<ResponseEntity<String>> getBlooped(@Valid @RequestBody WordRequest wordRequest) {
//        return clientRequestService.processData(wordRequest).map(ResponseEntity::ok);
//    }

    @PostMapping(value = "/bloop", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> processUserInput (@Valid @RequestBody WordRequest wordRequest) {
        log.info("User Input {}", wordRequest.getWord());
        String userInput = wordRequest.getWord();
        return clientRequestService.processUserInput(userInput);
    }
}
