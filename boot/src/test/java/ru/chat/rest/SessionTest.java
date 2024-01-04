package ru.chat.rest;

import io.restassured.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.chat.boot.AbstractSpringTest;
import ru.chat.core.context.session.UserSessionCache;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
public class SessionTest extends AbstractSpringTest {
    @Autowired
    UserSessionCache cache;

    @Test
    public void openSessionTest() {
        log.info("openSessionTest");
        String login = "ivanivanov";
        String sessionIdOld = UUID.randomUUID().toString();
        String sessionIdNew = UUID.randomUUID().toString();
        Cookie cookie = new Cookie.Builder("sessionId",sessionIdOld).build();
        given().queryParam("login",login)
                .cookie(cookie)
                .port(port)
                .urlEncodingEnabled(false)
                .when()
                .post("/session/open")
                .then()
                .statusCode(200);

        Assertions.assertTrue(cache.isPresent(login));
        Assertions.assertEquals(cache.getSession(login).getSessionId(), sessionIdOld);
        Assertions.assertEquals(cache.getSession(login).getLogin(), login);

        cookie = new Cookie.Builder("sessionId", sessionIdNew).build();

        given().queryParam("login",login)
                .cookie(cookie)
                .port(port)
                .urlEncodingEnabled(false)
                .when()
                .post("/session/open")
                .then()
                .statusCode(200);

        Assertions.assertEquals(sessionIdNew, cache.getSession(login).getSessionId());

    }
}
