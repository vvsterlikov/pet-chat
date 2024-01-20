package ru.chat.core.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chat.core.data.entity.UserSessionEntity;
import ru.chat.core.data.repository.UserSessionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSessionJpaService {

    private final UserSessionRepository repository;

    public List<UserSessionEntity> countByUserId(String userLogin) {
        return repository.countByUserLogin(userLogin);
    }

    public void save(UserSessionEntity userSessionEntity) {
        repository.save(userSessionEntity);
    }
}
