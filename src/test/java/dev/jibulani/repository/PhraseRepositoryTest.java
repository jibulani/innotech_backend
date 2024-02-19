package dev.jibulani.repository;

import dev.jibulani.model.Phrase;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhraseRepositoryTest {

    @Test
    public void shouldReturnOnlyOnePhraseByDefault() {
        PhraseRepository phraseRepository = new PhraseRepository();
        HashSet<Phrase> resultPhrases = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            resultPhrases.add(phraseRepository.getPhrase());
        }
        assertEquals(1, resultPhrases.size());
    }

    @Test
    public void shouldAddPhraseAndReturnNewAndDefaultPhrases() {
        PhraseRepository phraseRepository = new PhraseRepository();
        HashSet<Phrase> resultPhrases = new HashSet<>();
        Phrase newPhrase = new Phrase("new phrase");
        phraseRepository.addPhrase(newPhrase);
        for (int i = 0; i < 10; i++) {
            resultPhrases.add(phraseRepository.getPhrase());
        }
        assertEquals(2, resultPhrases.size());
        assertTrue(resultPhrases.contains(newPhrase));
    }
}
