package dev.jibulani.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DispatcherServletTest {

    private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private static final StringWriter writer = new StringWriter();

    @BeforeAll
    public static void setup() throws IOException {
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
    }

    @Test
    public void shouldDoSuccessMappingOfGetRequest() throws InvocationTargetException, IllegalAccessException, IOException {
        String expectedResult = "{\"phraseText\":\"У тебя все получится!\"}";
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        when(request.getRequestURI()).thenReturn("/help-service/v1/support");
        dispatcherServlet.doGet(request, response);
        assertEquals(expectedResult, writer.toString());
        verify(response).setStatus(200);
    }

    @Test
    public void shouldDoSuccessMappingOfPostRequest() throws InvocationTargetException, IllegalAccessException, IOException {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
        String newPhrase = """
                {
                    "phraseText": "У тебя все получится!"
                }
                """;
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(newPhrase)));
        when(request.getRequestURI()).thenReturn("/help-service/v1/support");
        dispatcherServlet.doPost(request, response);
        verify(response).setStatus(204);
    }

    @Test
    public void shouldNotFoundMappingOfGetRequest() throws InvocationTargetException, IllegalAccessException, IOException {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        when(request.getRequestURI()).thenReturn("/unsupported");
        dispatcherServlet.doGet(request, response);
        verify(response).setStatus(404);
    }

    @Test
    public void shouldNotFoundMappingOfPostRequest() throws InvocationTargetException, IllegalAccessException, IOException {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        when(request.getRequestURI()).thenReturn("/unsupported");
        dispatcherServlet.doPost(request, response);
        verify(response).setStatus(404);
    }
}
