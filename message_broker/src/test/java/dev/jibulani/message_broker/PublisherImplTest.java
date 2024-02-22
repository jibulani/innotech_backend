package dev.jibulani.message_broker;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PublisherImplTest {

    private final MessageBroker<String> messageBroker = mock(MessageBroker.class);
    private final Publisher<String> phrasePublisher = new PublisherImpl<>(messageBroker);

    @Test
    public void shouldAddMessageToBroker() {
        String testPhrase = "test";
        phrasePublisher.publish(testPhrase);
        verify(messageBroker).publish(testPhrase);
    }
}
