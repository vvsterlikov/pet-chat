package ru.chat.core.context.session;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UserSessionCache {

    @Autowired
    private Environment environment;

    private final Cache<String, UserSession> cache;

    public UserSessionCache(@Value("${cache.user.session.expiry}") long cacheExpiry) {
        cache = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(cacheExpiry, TimeUnit.SECONDS)
                .evictionListener((k, v, cause) -> {
                    log.info("Evict session: {}, cause: {}", k, v);
                })
                .build();
        log.info("set cache expiry to {}", cacheExpiry);
    }

    @Timed(value = "cache.user.session.upsert")
    public void upsertSession(UserSession userSession) {
        Optional.ofNullable(cache.getIfPresent(userSession.getLogin()))
                .ifPresentOrElse(s -> updateSession(s, userSession.getSessionId()),
                        () -> putSession(userSession));
    }

    public boolean isPresent(String login) {
        return cache.getIfPresent(login) != null;
    }

    public UserSession getSession(String login) {
        return cache.getIfPresent(login);
    }

    private void updateSession(UserSession currentUserSession, String newSessionId) {
        log.info("Found session for user {}, update sessionId, old: {} new: {}",
                currentUserSession.getLogin(), currentUserSession.getSessionId(), newSessionId);
        cache.put(currentUserSession.getLogin(), currentUserSession.toBuilder()
                .sessionId(newSessionId)
                .lastActivityTime(LocalDateTime.now())
                .build());
    }

    private void putSession(UserSession userSession) {
        log.info("Session not found, put new session into cache: {}", userSession);
        cache.put(userSession.getLogin(),
                UserSession.builder()
                        .login(userSession.getLogin())
                        .sessionId(userSession.getSessionId())
                        .lastActivityTime(LocalDateTime.now())
                        .build());
    }

}
