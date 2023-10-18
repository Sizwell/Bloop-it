package com.itech.clientrequestservices.contoller;

import com.itech.clientrequestservices.service.ClientRequestService;
import com.itech.clientrequestservices.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2")
@Slf4j
public class ClientRequestController {

    private final ClientRequestService clientRequestService;

    @GetMapping(value = "/blooped", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<String>> getBlooped() {
        return clientRequestService.getBlooped().map(ResponseEntity::ok);
    }
}
