package dev.jibulani.servlet;

import dev.jibulani.service.PhraseService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportServletTest {

    private static final SupportServlet supportServlet = new SupportServlet();
    private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private static final PhraseService phraseService = Mockito.mock(PhraseService.class);
    private static StringWriter writer;

    @BeforeAll
    public static void setup() throws IOException {
        writer = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(writer));
        supportServlet.phraseService = phraseService;
    }

    @Test
    public void shouldGetPhraseFromList() throws ServletException, IOException {
        String resultPhrase = "phrase";
        Mockito.when(phraseService.getPhrase()).thenReturn(resultPhrase);
        supportServlet.doGet(request, response);
        assertEquals(resultPhrase, writer.toString());
        Mockito.verify(phraseService).getPhrase();
    }

    @Test
    public void shouldPostPhraseToList() throws ServletException, IOException {
        String newPhrase = "new phrase";
        Mockito.when(request.getReader()).thenReturn(new BufferedReader(new StringReader(newPhrase)));
        supportServlet.doPost(request, response);
        Mockito.verify(phraseService).addPhrase(newPhrase);
    }
}
