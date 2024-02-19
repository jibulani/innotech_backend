package dev.jibulani.controller;

import dev.jibulani.configuration.Controller;
import dev.jibulani.configuration.GetMapping;
import dev.jibulani.configuration.PostMapping;
import dev.jibulani.model.Phrase;

@Controller
public interface SupportController {

    @GetMapping("/help-service/v1/support")
    Phrase getPhrase();

    @PostMapping("/help-service/v1/support")
    void addPhrase(Phrase phrase);
}
