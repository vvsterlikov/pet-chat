package ru.chat.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ru.chat.rest",
        "ru.chat.boot",
        "ru.chat.core",
})
@EnableJpaRepositories("ru.chat.core.data.repository")
@EntityScan("ru.chat.core.data.entity")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}