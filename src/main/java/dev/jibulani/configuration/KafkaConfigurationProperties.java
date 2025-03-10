package dev.jibulani.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfigurationProperties {

    private String topicName;
    private Boolean enabled;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public KafkaConfigurationProperties setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
