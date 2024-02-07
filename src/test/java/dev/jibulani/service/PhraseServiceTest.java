package dev.jibulani.service;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhraseServiceTest {

    @Test()
    public void shouldReturnOnlyOnePhraseByDefault() {
        PhraseService phraseService = new PhraseService();
        HashSet<String> resultPhrases = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            resultPhrases.add(phraseService.getPhrase());
        }
        assertEquals(1, resultPhrases.size());
    }

    @Test
    public void shouldAddPhraseAndReturnNewAndDefaultPhrases() {
        PhraseService phraseService = new PhraseService();
        HashSet<String> resultPhrases = new HashSet<>();
        String newPhrase = "new phrase";
        phraseService.addPhrase(newPhrase);
        for (int i = 0; i < 10; i++) {
            resultPhrases.add(phraseService.getPhrase());
        }
        assertEquals(2, resultPhrases.size());
        assertTrue(resultPhrases.contains(newPhrase));
    }
}
