package dev.jibulani.repository;

import dev.jibulani.model.Phrase;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Repository;

@Repository
public class PhraseRepository {

    public final List<Phrase> cheerUpPhrases = new CopyOnWriteArrayList<>() {{
        add(new Phrase("У тебя все получится!"));
    }};

    public Phrase getPhrase() {
        return cheerUpPhrases.get(
                ThreadLocalRandom.current().nextInt(cheerUpPhrases.size()) % cheerUpPhrases.size()
        );
    }

    public void addPhrase(Phrase phrase) {
        cheerUpPhrases.add(phrase);
    }
}
