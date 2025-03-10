package dev.jibulani.repository;

import dev.jibulani.model.Phrase;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Repository;

@Repository
public class PhraseRepository {

    public final Set<Phrase> cheerUpPhrases = new CopyOnWriteArraySet<>() {{
        add(new Phrase("У тебя все получится!"));
    }};

    public Phrase getPhrase() {
        return cheerUpPhrases.stream().toList().get(
                ThreadLocalRandom.current().nextInt(cheerUpPhrases.size()) % cheerUpPhrases.size()
        );
    }

    public void addPhrase(Phrase phrase) {
        cheerUpPhrases.add(phrase);
    }
}
