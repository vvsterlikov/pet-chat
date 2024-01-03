package ru.chat.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        //register(UserSessionControllerImpl.class);
        packages("ru.chat.rest");
    }
}
