package ru.chat.rest.controller.impl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.chat.rest.controller.UserSessionController;

@Slf4j
@Component
public class UserSessionControllerImpl implements UserSessionController {
    @Override
    @Path("/open")
    @POST
    public Response openSession(@QueryParam("login") String login,
                                @CookieParam("sessionId") String sessionId) {
        log.info("Opening user session");
        return Response.ok().build();
    }
}
