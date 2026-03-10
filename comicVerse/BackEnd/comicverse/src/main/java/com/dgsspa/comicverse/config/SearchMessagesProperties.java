package com.dgsspa.comicverse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "search.messages")
public class SearchMessagesProperties {

    private String noResults;

    public String getNoResults() {
        return noResults;
    }

    public void setNoResults(String noResults) {
        this.noResults = noResults;
    }
}
