package org.example;

import jakarta.validation.Valid;
import org.example.models.Post;
import org.example.models.User;
import org.example.services.PostService;
import org.example.services.UserService;
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


    @GetMapping("/api/posts") // Список страниц
    public ResponseEntity<List<Post>> allPosts(@RequestParam(defaultValue = "10") Integer limit) {
        var result = PostService.getAllPosts(limit);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/api/posts/{id}") //Получить пост
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        var post = PostService.find(id);
        return ResponseEntity.of(post);
    }

    @PostMapping("/api/posts") // Создать пост
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        PostService.create(post);
        ResponseEntity.ok("все получилось");
        return ResponseEntity.status(201).body(post);
    }

    @PutMapping("/api/posts/{id}") // Обновить пост
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post data) {
        PostService.updatePost(id, data);
        return ResponseEntity.status(200).body(data);
    }

    @DeleteMapping("/api/posts/{id}") //Удалить
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
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