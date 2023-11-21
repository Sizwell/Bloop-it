package com.itech.clientrequestservices.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.itech.clientrequestservices.config.UserRequestConfig;
import com.itech.clientrequestservices.dto.WordDto;
import com.itech.clientrequestservices.properties.Properties;
import com.itech.clientrequestservices.service.ClientRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2")
@Slf4j
public class ClientRequestController {

    private final ClientRequestService clientRequestService;
    private final UserRequestConfig userRequestConfig;

    @PostMapping(value = "/bloop", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> processUserInput (@Valid @RequestBody WordDto wordDto) {
        String userInput = wordDto.getWord();
        return clientRequestService.processUserInput(userInput);
    }

    @GetMapping("/bloop/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(userRequestConfig.getMsg(), userRequestConfig.getBuildVersion(),
                userRequestConfig.getMailDetails());
        return ow.writeValueAsString(properties);
    }
}
