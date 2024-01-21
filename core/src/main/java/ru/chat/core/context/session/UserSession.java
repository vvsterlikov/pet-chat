package ru.chat.core.context.session;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class UserSession {
    private String sessionId;

    private String login;

    @Builder.Default
    private UserSessionStatus status = UserSessionStatus.OPENED;

    @Builder.Default
    private LocalDateTime lastActivityTime = LocalDateTime.now();

}
