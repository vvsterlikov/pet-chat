package ru.chat.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.chat.rest.JerseyConfig;


@Slf4j
public class SpringAppTest extends AbstractSpringTest {

    @Autowired
    ApplicationContext context;
    @Test
    public void createContext() {
        Assertions.assertNotNull(context);
        log.info("Got Spring context!");
    }

    @Test
    public void createBeanTest() {
        Assertions.assertNotNull(context.getBean(JerseyConfig.class));
    }
}
