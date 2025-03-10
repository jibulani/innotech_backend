package dev.jibulani.model;

import java.util.Objects;

public class Phrase {

    private String phraseText;

    public Phrase() {}

    public Phrase(String phraseText) {
        this.phraseText = phraseText;
    }

    public String getPhraseText() {
        return phraseText;
    }

    public Phrase setPhraseText(String phraseText) {
        this.phraseText = phraseText;
        return this;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "phraseText='" + phraseText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phrase phrase = (Phrase) o;
        return Objects.equals(phraseText, phrase.phraseText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phraseText);
    }
}
