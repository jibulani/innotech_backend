package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import dev.jibulani.service.PhraseService;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportControllerImplTest {

    private static final PhraseService phraseService = Mockito.mock(PhraseService.class);
    private final SupportController supportController = new SupportControllerImpl(phraseService);

    @Test
    public void shouldGetPhraseFromList() throws ServletException, IOException {
        String resultPhrase = "phrase";
        Mockito.when(phraseService.getPhrase()).thenReturn(resultPhrase);
        Phrase phrase = supportController.getPhrase();
        assertEquals(new Phrase(resultPhrase), phrase);
        Mockito.verify(phraseService).getPhrase();
    }

    @Test
    public void shouldPostPhraseToList() throws ServletException, IOException {
        String newPhrase = "new phrase";
        supportController.addPhrase(new Phrase(newPhrase));
        Mockito.verify(phraseService).addPhrase(newPhrase);
    }
}
