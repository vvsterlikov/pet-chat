package ru.chat.rest.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.chat.core.context.session.UserSession;
import ru.chat.core.context.session.UserSessionStatus;
import ru.chat.core.data.entity.UserSessionEntity;

@Mapper(componentModel = "spring")
public interface UserSessionMapper {

    @Mapping(target = "userLogin", source = "login")
    @Mapping(target = "status", qualifiedByName = "status")
    @Mapping(target = "sessionUUID", source = "sessionId")
    UserSessionEntity dtoToEntity(UserSession userSession);

    @Named("status")
    default String status(UserSessionStatus status) {
        return status.toString();
    }
}
