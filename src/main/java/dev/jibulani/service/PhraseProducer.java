package dev.jibulani.service;

import dev.jibulani.configuration.KafkaConfigurationProperties;
import dev.jibulani.model.Phrase;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class PhraseProducer {

    private static final Logger logger = LoggerFactory.getLogger(PhraseProducer.class);
    private final KafkaTemplate<String, Phrase> kafkaTemplate;
    private final KafkaConfigurationProperties kafkaConfigurationProperties;

    public PhraseProducer(
            KafkaTemplate<String, Phrase> kafkaTemplate,
            KafkaConfigurationProperties kafkaConfigurationProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigurationProperties = kafkaConfigurationProperties;
    }

    public void produce(Phrase phrase) {
        kafkaTemplate.executeInTransaction(kt -> {
            CompletableFuture<SendResult<String, Phrase>> sendFutureResult = kt.send(
                    kafkaConfigurationProperties.getTopicName(), phrase.getPhraseText(), phrase
            );
            sendFutureResult.whenComplete(((sendResult, throwable) -> {
                if (throwable != null) {
                    logger.error("Error while sending phrase to kafka", throwable);
                }
            }));
            return sendFutureResult;
        });
    }
}
