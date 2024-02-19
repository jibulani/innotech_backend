package dev.jibulani;

import dev.jibulani.configuration.Configuration;
import dev.jibulani.configuration.Instance;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.reflections.Reflections;

public class ApplicationContext {

    private static final Map<Class<?>, Object> INSTANCES = new HashMap<>();

    public ApplicationContext() throws InvocationTargetException, IllegalAccessException {
        final var configurations = getConfigurations();
        for (Object config : configurations) {
            for (Method method : Arrays.stream(config.getClass().getMethods()).filter(method -> method.isAnnotationPresent(Instance.class) && method.getParameters().length == 0).toList()) {
                INSTANCES.put(method.getReturnType(), wrapWithLogging(method.invoke(config)));
            }
            for (Method method : Arrays.stream(config.getClass().getMethods()).filter(method -> method.isAnnotationPresent(Instance.class) && method.getParameters().length != 0).toList()) {
                final var params = new ArrayList<>();
                for (Parameter parameter : method.getParameters()) {
                    params.add(INSTANCES.get(parameter.getType()));
                }
                INSTANCES.put(method.getReturnType(), wrapWithLogging(method.invoke(config, params.toArray())));
            }
        }
    }

    private List<?> getConfigurations() {
        Reflections reflections = new Reflections("dev.jibulani.configuration");
        return reflections.getTypesAnnotatedWith(Configuration.class)
                .stream()
                .map(type -> {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    private Object wrapWithLogging(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new LoggingInvocationHandler(object)
        );
    }

    public <T> T getInstance(Class<T> tClass) {
        return (T) Optional.ofNullable(INSTANCES.get(tClass))
                .orElseThrow(() -> new RuntimeException("No instance with such type: %s".formatted(tClass)));
    }
}
