package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import dev.jibulani.service.PhraseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportControllerImplTest {

    private static final PhraseService phraseService = Mockito.mock(PhraseService.class);
    private final SupportController supportController = new SupportControllerImpl(phraseService);

    @Test
    public void shouldGetPhraseFromList() {
        Phrase resultPhrase = new Phrase("phrase");
        Mockito.when(phraseService.getPhrase()).thenReturn(resultPhrase);
        Phrase phrase = supportController.getPhrase();
        assertEquals(resultPhrase, phrase);
        Mockito.verify(phraseService).getPhrase();
    }

    @Test
    public void shouldPostPhraseToList() {
        Phrase newPhrase = new Phrase("new phrase");
        supportController.addPhrase(newPhrase);
        Mockito.verify(phraseService).addPhrase(newPhrase);
    }
}
