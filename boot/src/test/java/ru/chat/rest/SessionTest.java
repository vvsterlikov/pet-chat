package ru.chat.rest;

import io.restassured.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.chat.boot.AbstractSpringTest;

import static io.restassured.RestAssured.given;

@Slf4j
public class SessionTest extends AbstractSpringTest {
    @Test
    public void openSessionTest() {
        log.info("openSessionTest");
        Cookie cookie = new Cookie.Builder("sessionId","11").build();
        given().queryParam("login","ivanivanov")
                .cookie(cookie)
                .port(port)
                .urlEncodingEnabled(false)
                .when()
                .post("/session/open")
                .then()
                .statusCode(200);
    }
}
