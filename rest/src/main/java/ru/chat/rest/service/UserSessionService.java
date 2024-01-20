package ru.chat.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chat.core.context.session.UserSession;
import ru.chat.core.context.session.UserSessionCache;
import ru.chat.core.data.service.UserSessionJpaService;
import ru.chat.rest.service.mapper.UserSessionMapper;

@Service
@RequiredArgsConstructor
public class UserSessionService {

    private final UserSessionCache cache;
    private final UserSessionJpaService userSessionJpaService;
    private final UserSessionMapper userSessionMapper;

    public void upsertSession(UserSession userSession) {
        if (!cache.isPresent(userSession.getLogin())) {
            saveUserSession(userSession);
        }
        cache.upsertSession(userSession);
    }

    private void saveUserSession(UserSession userSession) {
        userSessionJpaService.save(userSessionMapper.dtoToEntity(userSession));
    }
}
