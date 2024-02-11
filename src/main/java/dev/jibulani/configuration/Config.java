package dev.jibulani.configuration;

import dev.jibulani.controller.SupportController;
import dev.jibulani.controller.SupportControllerImpl;
import dev.jibulani.service.PhraseService;
import dev.jibulani.service.PhraseServiceImpl;

@Configuration
public class Config {

    @Instance
    public PhraseService phraseService() {
        return new PhraseServiceImpl();
    }

    @Instance
    public SupportController supportController(PhraseService phraseService) {
        return new SupportControllerImpl(phraseService);
    }
}
