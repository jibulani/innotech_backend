package dev.jibulani;

import dev.jibulani.controller.SupportController;
import dev.jibulani.service.PhraseService;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {

    @Test
    public void shouldGetInstanceWithoutParamsFromContext() throws InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = new ApplicationContext();
        PhraseService instance = applicationContext.getInstance(PhraseService.class);
        assertNotNull(instance);
        instance.getPhrase();
    }

    @Test
    public void shouldGetInstanceWithParamsFromContext() throws InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = new ApplicationContext();
        assertNotNull(applicationContext.getInstance(SupportController.class));
    }
}
