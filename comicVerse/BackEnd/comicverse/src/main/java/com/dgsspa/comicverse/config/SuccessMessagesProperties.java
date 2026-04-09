package com.dgsspa.comicverse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "messages.success")
public class SuccessMessagesProperties {

    private String deleted;
    private String created;
    private String updated;

    public String getDeleted() { return deleted; }
    public String getCreated() { return created; }
    public String getUpdated() { return updated; }

    public void setDeleted(String deleted) { this.deleted = deleted; }
    public void setCreated(String created) { this.created = created; }
    public void setUpdated(String updated) { this.updated = updated; }
}