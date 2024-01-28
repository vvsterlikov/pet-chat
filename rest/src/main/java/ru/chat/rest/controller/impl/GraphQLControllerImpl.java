package ru.chat.rest.controller.impl;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.chat.rest.controller.GraphQLController;

@Slf4j
@Component
@Path("/")
public class GraphQLControllerImpl implements GraphQLController {

    @Override
    @POST
    @Path("/graphql")
    public Response execute() {
        log.info("execute!");
        return Response.ok().build();
    }
}
