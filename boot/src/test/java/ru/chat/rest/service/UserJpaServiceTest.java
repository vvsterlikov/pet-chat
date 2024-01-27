package ru.chat.rest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.chat.boot.AbstractSpringTest;
import ru.chat.core.data.service.UserJpaService;

@Slf4j
public class UserJpaServiceTest extends AbstractSpringTest {

    @Autowired
    private UserJpaService userJpaService;

    private final static String TEST_LOGIN = "petrov";
    @Test
    public void queryUsersTest() {
        Assertions.assertFalse(userJpaService.findByLogin(TEST_LOGIN).isEmpty());
    }
}
