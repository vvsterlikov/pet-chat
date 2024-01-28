package ru.chat.rest.controller;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

public interface GraphQLController {
    @POST
    @Path("/graphql")
    Response execute();
}
