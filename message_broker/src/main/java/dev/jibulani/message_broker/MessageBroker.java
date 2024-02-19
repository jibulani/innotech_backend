package dev.jibulani.message_broker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageBroker<T> {

    private final BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public void publish(T message) {
        queue.add(message);
    }

    public T poll() {
        return queue.poll();
    }
}
