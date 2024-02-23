package com.itech.sensitivewordsservice.internal.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "sensitive-word-service")
@Getter
@Setter
@ToString
public class SensitiveWordsConfig {

    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
}
