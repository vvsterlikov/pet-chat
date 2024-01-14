package ru.chat.boot;

import io.micrometer.core.instrument.MeterRegistry;
import io.restassured.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureObservability
@Slf4j
public abstract class AbstractSpringTest {
    @LocalServerPort
    protected int port;

    @Autowired
    MeterRegistry meterRegistry;

    @AfterEach
    public void afterEach() {
        log.info("printing metrics");
        meterRegistry.forEachMeter(meter -> {
            meter.measure().forEach(m -> {
                log.info("{} = {}",
                        meter.getId().getName(),
                        m.getValue());
            });
        });
    }


}
