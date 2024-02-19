package dev.jibulani.service;

import dev.jibulani.message_broker.MessageBroker;
import dev.jibulani.model.Phrase;
import dev.jibulani.repository.PhraseRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhraseSubscriberTest {

    private final MessageBroker<Phrase> messageBroker = mock(MessageBroker.class);
    private final PhraseRepository phraseRepository = mock(PhraseRepository.class);

    public final PhraseSubscriber phraseSubscriber = new PhraseSubscriber(messageBroker, phraseRepository);

    @Test
    public void shouldSavePhraseFromMessageBroker() {
        Phrase testPhrase = new Phrase("test");
        when(messageBroker.poll()).thenReturn(testPhrase);
        phraseSubscriber.subscribe();
        verify(messageBroker).poll();
        verify(phraseRepository).addPhrase(testPhrase);
    }

    @Test
    public void shouldDoNothingIfMessageBrokerIsEmpty() {
        when(messageBroker.poll()).thenReturn(null);
        phraseSubscriber.subscribe();
        verify(messageBroker).poll();
        verify(phraseRepository, never()).addPhrase(any());
    }
}
