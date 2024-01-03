package ru.chat.rest.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/session")
public interface UserSessionController {
    @POST
    @Path("/open")
    Response openSession(@QueryParam("login") String login,
                         @CookieParam("sessionId") String sessionId);
}
