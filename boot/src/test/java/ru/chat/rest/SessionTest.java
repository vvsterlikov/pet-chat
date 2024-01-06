package ru.chat.rest;

import io.restassured.http.Cookie;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.chat.boot.AbstractSpringTest;
import ru.chat.core.context.session.UserSession;
import ru.chat.core.context.session.UserSessionCache;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SessionTest extends AbstractSpringTest {
    @Autowired
    private UserSessionCache cache;

    private List<UserSession> uuids;

    private final int SESSION_COUNT = 10;

    @BeforeAll
    private void beforeAll() {
        uuids = new ArrayList<>();
        for (int i = 0; i <= SESSION_COUNT; i++) {
            uuids.add(UserSession.builder()
                            .login("ivanov"+i)
                            .sessionId(UUID.randomUUID().toString())
                    .build());
            given().queryParam("login",uuids.get(i).getLogin())
                    .cookie(new Cookie.Builder("sessionId",uuids.get(i).getSessionId()).build())
                    .port(port)
                    .urlEncodingEnabled(false)
                    .when()
                    .post("/session/open")
                    .then()
                    .statusCode(200);
        }
    }


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

    @Test
    public void expiryAllTest() {

    }
}
