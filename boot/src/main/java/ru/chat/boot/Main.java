package ru.chat.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.chat.rest", "ru.chat.boot", "ru.chat.core"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}