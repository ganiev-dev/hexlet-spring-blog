package org.example;

import jakarta.validation.Valid;
import org.example.models.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
@RestController
public class App {
    // Хранилище добавленных постов, то есть обычный список
    private List<Post> posts = new ArrayList<Post>();


    //Запуск приложения
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/posts") // Список страниц
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().limit(limit).toList();
    }

    @PostMapping("/posts")
    public Post create(@Valid @RequestBody Post post) {
        post.setCreatedAt(LocalDateTime.now());
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePage = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybePage.isPresent()) {
            var page = maybePage.get();
             page.setTitle(data.getTitle());
            page.setContent(data.getContent());
            page.setContent(data.getContent());
            page.setAuthor(data.getAuthor());
            page.setCreatedAt(LocalDateTime.now());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}") //Удалить
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }

    @GetMapping("/posts/{id}") //Получить пост
    public Optional<Post> show(@PathVariable String id) {
        var page = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return page;
    }



    @GetMapping("/")
    public String home() {
        return "Добро пожаловать в Hexlet Spring Blog!";
    }
    @GetMapping("/about")
    public String about() {
        return "Пока это просто текстовый проект на спринге";
    }


}