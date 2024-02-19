package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import dev.jibulani.service.PhraseService;

public class SupportControllerImpl implements SupportController {

    public PhraseService phraseService;

    public SupportControllerImpl(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @Override
    public Phrase getPhrase() {
        return new Phrase(phraseService.getPhrase());
    }

    @Override
    public void addPhrase(Phrase phrase) {
        phraseService.addPhrase(phrase.phraseText());
    }
}
