package dev.jibulani.configuration;

import dev.jibulani.message_broker.MessageBroker;
import dev.jibulani.message_broker.Publisher;
import dev.jibulani.message_broker.PublisherImpl;
import dev.jibulani.model.Phrase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MessageBroker<Phrase> messageBroker() {
        return new MessageBroker<>();
    }

    @Bean
    public Publisher<Phrase> publisher(MessageBroker<Phrase> messageBroker) {
        return new PublisherImpl<>(messageBroker);
    }
}
