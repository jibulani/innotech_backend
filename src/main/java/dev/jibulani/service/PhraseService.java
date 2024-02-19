package dev.jibulani.service;

import dev.jibulani.configuration.Logged;
import java.util.concurrent.ThreadLocalRandom;

public interface PhraseService {

    @Logged
    String getPhrase();

    @Logged
    void addPhrase(String phrase);
}
