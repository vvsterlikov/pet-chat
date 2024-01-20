package ru.chat.core.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chat.core.data.entity.UserSessionEntity;

import java.util.List;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSessionEntity, Long> {

    List<UserSessionEntity> countByUserLogin(String userLogin);
}
