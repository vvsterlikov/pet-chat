package ru.chat.core.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name ="USER_SESSION")
@Getter
@Setter
public class UserSessionEntity {
    @Id
    private Long id;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SESSION_UUID")
    private String sessionUUID;
}
