package dev.jibulani.service;

import dev.jibulani.message_broker.Subscriber;
import dev.jibulani.message_broker.MessageBroker;
import dev.jibulani.model.Phrase;
import dev.jibulani.repository.PhraseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PhraseSubscriber {

    private static final Logger logger = LoggerFactory.getLogger(PhraseSubscriber.class);
    private final MessageBroker<Phrase> messageBroker;
    private final PhraseRepository repository;


    public PhraseSubscriber(MessageBroker<Phrase> messageBroker, PhraseRepository phraseRepository) {
        this.messageBroker = messageBroker;
        this.repository = phraseRepository;
    }

    @Subscriber
    public Phrase subscribe() {
        logger.info("Start polling message broker");
        Phrase phrase = messageBroker.poll();
        if (phrase != null) {
            repository.addPhrase(phrase);
        }
        return phrase;
    }
}
