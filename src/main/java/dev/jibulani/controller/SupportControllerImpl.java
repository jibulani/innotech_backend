package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import dev.jibulani.repository.PhraseRepository;
import dev.jibulani.message_broker.Publisher;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportControllerImpl implements SupportController {

    public final Publisher<Phrase> phraseService;
    public final PhraseRepository phraseRepository;

    public SupportControllerImpl(Publisher<Phrase> phraseService, PhraseRepository phraseRepository) {
        this.phraseService = phraseService;
        this.phraseRepository = phraseRepository;
    }

    @Override
    public Phrase getPhrase() {
        return phraseRepository.getPhrase();
    }

    @Override
    public void addPhrase(Phrase phrase) {
        phraseService.publish(phrase);
    }
}
