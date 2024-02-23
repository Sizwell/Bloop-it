package com.itech.sensitivewordsservice.internal.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Properties {

    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;

    public Properties(String msg, String buildVersion, Map<String, String> mailDetails) {
        this.msg = msg;
        this.buildVersion = buildVersion;
        this.mailDetails = mailDetails;
    }
}
