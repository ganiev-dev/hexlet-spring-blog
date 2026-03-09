package org.example;

import jakarta.validation.Valid;
import org.example.models.Post;
import org.example.services.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class App {

    //Запуск приложения
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/posts") // Список страниц
    public ResponseEntity<List<Post>> allPosts(@RequestParam(defaultValue = "10") Integer limit) {
        var result = PostService.getAllPosts(limit);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/posts/{id}") //Получить пост
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        var post = PostService.find(id);
        return ResponseEntity.of(post);
    }

    @PostMapping("/posts") // Создать пост
    public ResponseEntity<Post> create(@Valid @RequestBody Post post) {
        PostService.create(post);
        ResponseEntity.ok("все получилось");
        return ResponseEntity.status(201).body(post);
    }

    @PutMapping("/posts/{id}") // Обновить пост
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        PostService.updatePost(id, data);
        return ResponseEntity.status(200).body(data);
    }

    @DeleteMapping("/posts/{id}") //Удалить
    public ResponseEntity<Void> delete(@PathVariable String id) {
        PostService.deletePost(id);
        return ResponseEntity.status(204).body(null);
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