package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import dev.jibulani.service.PhraseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportControllerImpl implements SupportController {
    public final PhraseService phraseService;

    public SupportControllerImpl(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @Override
    public Phrase getPhrase() {
        return phraseService.getPhrase();
    }

    @Override
    public void addPhrase(Phrase phrase) {
        phraseService.addPhrase(phrase);
    }
}
