package ru.chat.core.data.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chat.core.data.entity.ChatUserEntity;
import ru.chat.core.data.repository.ChatUserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserJpaService {

    private final ChatUserRepository userRepository;

    public List<ChatUserEntity> findByLogin(String login) {
        return  userRepository.findByLogin(login);
    }

}
