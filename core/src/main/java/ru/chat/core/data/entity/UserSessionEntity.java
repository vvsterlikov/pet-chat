package ru.chat.core.data.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="USER_SESSION")
@Getter
@Setter
public class UserSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SESSION_UUID")
    private String sessionUUID;
}
