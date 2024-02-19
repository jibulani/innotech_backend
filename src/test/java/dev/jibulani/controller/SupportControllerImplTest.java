package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import dev.jibulani.repository.PhraseRepository;
import dev.jibulani.message_broker.Publisher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportControllerImplTest {

    private static final Publisher<Phrase> phrasePublisher = Mockito.mock(Publisher.class);
    private static final PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
    private final SupportController supportController = new SupportControllerImpl(phrasePublisher, phraseRepository);

    @Test
    public void shouldGetPhraseFromList() {
        Phrase resultPhrase = new Phrase("phrase");
        Mockito.when(phraseRepository.getPhrase()).thenReturn(resultPhrase);
        Phrase phrase = supportController.getPhrase();
        assertEquals(resultPhrase, phrase);
        Mockito.verify(phraseRepository).getPhrase();
    }

    @Test
    public void shouldPostPhraseToList() {
        Phrase newPhrase = new Phrase("new phrase");
        supportController.addPhrase(newPhrase);
        Mockito.verify(phrasePublisher).publish(newPhrase);
    }
}
