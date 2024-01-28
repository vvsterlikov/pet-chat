package ru.chat.core.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chat.core.data.entity.ChatUserEntity;

import java.util.List;

public interface ChatUserRepository extends CrudRepository<ChatUserEntity, Long> {

    List<ChatUserEntity> findByLogin(String login);
}
