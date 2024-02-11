package dev.jibulani.service;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhraseServiceImplTest {

    @Test()
    public void shouldReturnOnlyOnePhraseByDefault() {
        PhraseServiceImpl phraseServiceImpl = new PhraseServiceImpl();
        HashSet<String> resultPhrases = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            resultPhrases.add(phraseServiceImpl.getPhrase());
        }
        assertEquals(1, resultPhrases.size());
    }

    @Test
    public void shouldAddPhraseAndReturnNewAndDefaultPhrases() {
        PhraseServiceImpl phraseServiceImpl = new PhraseServiceImpl();
        HashSet<String> resultPhrases = new HashSet<>();
        String newPhrase = "new phrase";
        phraseServiceImpl.addPhrase(newPhrase);
        for (int i = 0; i < 10; i++) {
            resultPhrases.add(phraseServiceImpl.getPhrase());
        }
        assertEquals(2, resultPhrases.size());
        assertTrue(resultPhrases.contains(newPhrase));
    }
}
