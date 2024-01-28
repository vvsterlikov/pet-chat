package ru.chat.rest.graphql;

import io.restassured.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.chat.boot.AbstractSpringTest;

import static io.restassured.RestAssured.given;

@Slf4j
public class GraphQLTest extends AbstractSpringTest {
    @Test
    public void test() {
        given()
                .urlEncodingEnabled(false)
                .port(port)
                .when()
                .post("/graphql")
                .then()
                .statusCode(200);
    }
}
