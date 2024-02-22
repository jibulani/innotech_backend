package dev.jibulani.controller;

import dev.jibulani.model.Phrase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SupportController {

    @GetMapping("/help-service/v1/support")
    Phrase getPhrase();

    @PostMapping("/help-service/v1/support")
    void addPhrase(@RequestBody Phrase phrase);
}
