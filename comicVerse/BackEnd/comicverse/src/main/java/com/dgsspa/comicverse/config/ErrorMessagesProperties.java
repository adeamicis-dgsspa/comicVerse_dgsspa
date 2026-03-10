package com.dgsspa.comicverse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "errors.not-found")
public class ErrorMessagesProperties {

    private String fumettoId;

    public String getFumettoId() {
        return fumettoId;
    }

    public void setFumettoId(String fumettoId) {
        this.fumettoId = fumettoId;
    }
}
