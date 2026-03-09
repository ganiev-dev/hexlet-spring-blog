package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
    @GetMapping("/")
    public String home() {
        return "Добро пожаловать в Hexlet Spring Blog!";
    }
    @GetMapping("/about")
    public String about() {
        return "Пока это просто текстовый проект на спринге";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}