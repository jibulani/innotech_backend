package dev.jibulani.service;

import dev.jibulani.configuration.KafkaConfigurationProperties;
import dev.jibulani.model.Phrase;
import dev.jibulani.repository.PhraseRepository;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {

    private final PhraseProducer phraseProducer;
    private final PhraseRepository phraseRepository;
    private final KafkaConfigurationProperties kafkaConfigurationProperties;

    public PhraseService(
            PhraseProducer phraseProducer,
            PhraseRepository phraseRepository,
            KafkaConfigurationProperties kafkaConfigurationProperties
    ) {
        this.phraseProducer = phraseProducer;
        this.phraseRepository = phraseRepository;
        this.kafkaConfigurationProperties = kafkaConfigurationProperties;
    }


    public void addPhrase(Phrase phrase) {
        if (kafkaConfigurationProperties.getEnabled()) {
            phraseProducer.produce(phrase);
        } else {
            phraseRepository.addPhrase(phrase);
        }
    }

    public Phrase getPhrase() {
        return phraseRepository.getPhrase();
    }
}
