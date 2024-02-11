package dev.jibulani.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PhraseServiceImpl implements PhraseService {

    public final List<String> cheerUpPhrases = new CopyOnWriteArrayList<>() {{
        add("У тебя все получится!");
    }};

    @Override
    public String getPhrase() {
        return cheerUpPhrases.get(
                ThreadLocalRandom.current().nextInt(cheerUpPhrases.size()) % cheerUpPhrases.size()
        );
    }

    @Override
    public void addPhrase(String phrase) {
        cheerUpPhrases.add(phrase);
    }
}
