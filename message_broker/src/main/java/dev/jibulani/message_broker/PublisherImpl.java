package dev.jibulani.message_broker;


public class PublisherImpl<T> implements Publisher<T> {
    private final MessageBroker<T> messageBroker;

    public PublisherImpl(MessageBroker<T> messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void publish(T message) {
        messageBroker.publish(message);
    }
}
