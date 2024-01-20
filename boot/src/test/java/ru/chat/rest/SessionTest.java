package ru.chat.rest;

import io.restassured.http.Cookie;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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

    private List<UserSession> userSessions;

    private final int SESSION_COUNT = 10;
    private final String SAMPLE_LOGIN = "ivanov";

    @Value("${cache.user.session.expiry}")
    private long cacheExpiry;

    @BeforeEach
    public void beforeEach() {
        init();
    }

    private void init() {
        userSessions = new ArrayList<>();
        for (int i = 0; i <= SESSION_COUNT; i++) {
            userSessions.add(UserSession.builder()
                    .login(SAMPLE_LOGIN + i)
                    .sessionId(UUID.randomUUID().toString())
                    .build());
            given().queryParam("login", userSessions.get(i).getLogin())
                    .cookie(new Cookie.Builder("sessionId", userSessions.get(i).getSessionId()).build())
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
        Assertions.assertTrue(cache.isPresent(SAMPLE_LOGIN + "1"));
        Assertions.assertEquals(userSessions.get(0).getSessionId(), cache.getSession(SAMPLE_LOGIN + "0").getSessionId());
        Assertions.assertEquals(SAMPLE_LOGIN + "1", cache.getSession(SAMPLE_LOGIN + "1").getLogin());
    }

    @Test
    @SneakyThrows
    public void expiryAllTest() {
        Thread.sleep((cacheExpiry + 1) * 1000);
        userSessions.forEach(s -> Assertions.assertFalse(cache.isPresent(s.getLogin())));
    }

    @Test
    public void expiryAllExceptOne() throws InterruptedException {
        Thread.sleep((cacheExpiry + 1) * 1000 / 2);
        cache.isPresent(SAMPLE_LOGIN + "1");
        cache.upsertSession(userSessions.get(0));
        Thread.sleep((cacheExpiry + 1) * 1000 / 2);
        long cachedCount = userSessions.stream()
                .filter(s -> cache.isPresent(s.getLogin()))
                .count();
        Assertions.assertEquals(1, cachedCount);

        Thread.sleep((cacheExpiry + 1) * 1000);
        Assertions.assertEquals(0, userSessions.stream()
                                            .filter(s -> cache.isPresent(s.getLogin()))
                                            .count());



    }
}
