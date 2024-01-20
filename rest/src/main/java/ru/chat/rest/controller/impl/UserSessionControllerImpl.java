package ru.chat.rest.controller.impl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.chat.core.context.session.UserSession;
import ru.chat.core.context.session.UserSessionCache;
import ru.chat.rest.controller.UserSessionController;
import ru.chat.rest.service.UserSessionService;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSessionControllerImpl implements UserSessionController {

    private final UserSessionService userSessionService;

    @Override
    @Path("/open")
    @POST
    public Response openSession(@QueryParam("login") String login,
                                @CookieParam("sessionId") String sessionId) {
        log.info("Opening user session: login = {}, sessionId = {}", login, sessionId);
        userSessionService.upsertSession(UserSession.builder()
                        .login(login)
                        .sessionId(sessionId)
                        .build());
        return Response.ok().build();
    }
}
