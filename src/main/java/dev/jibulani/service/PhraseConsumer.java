package dev.jibulani.service;

import dev.jibulani.model.Phrase;
import dev.jibulani.repository.PhraseRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;

public class PhraseConsumer {

    private final PhraseRepository phraseRepository;


    public PhraseConsumer(
            PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    @Transactional
    @KafkaListener(topics = "${kafka.topic-name}", groupId = "${spring.kafka.consumer.group-id}", properties = {"spring.json.value.default.type=dev.jibulani.model.Phrase"})
    public Phrase consume(Phrase phrase) {
        phraseRepository.addPhrase(phrase);
        return phrase;
    }
}
