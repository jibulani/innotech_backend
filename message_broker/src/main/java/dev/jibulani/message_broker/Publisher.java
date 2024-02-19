package dev.jibulani.message_broker;

public interface Publisher<T> {

    void publish(T message);
}
