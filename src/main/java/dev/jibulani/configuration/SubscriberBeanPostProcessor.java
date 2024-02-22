package dev.jibulani.configuration;

import dev.jibulani.message_broker.Subscriber;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SubscriberBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Object> beanNameTarget = new HashMap<>();
    private final Long SUBSCRIBE_SLEEP_MILLIS = 5000L;
    private final ApplicationContext applicationContext;

    public SubscriberBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Optional<Method> subscriberMethod = Arrays.stream(bean.getClass().getMethods()).filter(method -> method.isAnnotationPresent(Subscriber.class)).findAny();
        if (subscriberMethod.isPresent()) {
            beanNameTarget.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object target = beanNameTarget.get(beanName);
        if (target != null) {
            Arrays.stream(target.getClass().getMethods()).filter(method -> method.isAnnotationPresent(Subscriber.class)).forEach(method -> {
                final var params = new ArrayList<>();
                for (Parameter parameter : method.getParameters()) {
                    params.add(applicationContext.getBean(parameter.getType()));
                }
                Runnable runnable = () -> {
                    while (true) {
                        try {
                            Object invoke = method.invoke(target, params.toArray());
                            if (invoke == null) {
                                Thread.sleep(SUBSCRIBE_SLEEP_MILLIS);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                new Thread(runnable).start();
            });
        }
        return bean;
    }
}
