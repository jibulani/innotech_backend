package dev.jibulani.configuration;

import dev.jibulani.repository.PhraseRepository;
import dev.jibulani.service.PhraseConsumer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    @ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
    public PhraseConsumer phraseConsumer(PhraseRepository phraseRepository) {
        return new PhraseConsumer(phraseRepository);
    }
}
