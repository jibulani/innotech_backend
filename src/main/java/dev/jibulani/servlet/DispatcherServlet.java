package dev.jibulani.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jibulani.ApplicationContext;
import dev.jibulani.configuration.Controller;
import dev.jibulani.configuration.GetMapping;
import dev.jibulani.configuration.PostMapping;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

public class DispatcherServlet extends HttpServlet {

    private final ApplicationContext applicationContext = new ApplicationContext();
    private final Map<Class<?>, Object> controllers = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DispatcherServlet() throws InvocationTargetException, IllegalAccessException {
    }

    @Override
    public void init() {
        Reflections reflections = new Reflections("dev.jibulani");
        reflections.getTypesAnnotatedWith(Controller.class).forEach(type -> {
            if (type.isInterface()) {
                controllers.put(type, applicationContext.getInstance(type));
            }
        });
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        controllers.forEach((type, object) -> {
            Optional<Method> first = Arrays.stream(type.getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(GetMapping.class) &&
                                    method.getAnnotation(GetMapping.class).value().equals(req.getRequestURI()))
                    .findFirst();
            if (first.isPresent()) {
                try {
                    Object invoke = first.get().invoke(object);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    resp.getWriter().print(objectMapper.writeValueAsString(invoke));
                } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                    resp.setStatus(500);
                }
            } else {
                resp.setStatus(404);
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        controllers.forEach((type, object) -> {
            Optional<Method> first = Arrays.stream(type.getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(PostMapping.class) &&
                                    method.getAnnotation(PostMapping.class).value().equals(req.getRequestURI()))
                    .findFirst();
            if (first.isPresent()) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    req.getReader().lines().forEach(stringBuilder::append);
                    Optional<Class<?>> param = Arrays.stream(first.get().getParameterTypes()).findFirst();
                    Object result;
                    if (param.isPresent()) {
                        result = first.get().invoke(object, objectMapper.readValue(stringBuilder.toString(), param.get()));
                    } else {
                        result = first.get().invoke(object);
                    }
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    if (result != null) {
                        resp.setStatus(200);
                        resp.getWriter().print(objectMapper.writeValueAsString(result));
                    } else {
                        resp.setStatus(204);
                    }
                } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                    resp.setStatus(500);
                }
            } else {
                resp.setStatus(404);
            }
        });
    }
}
