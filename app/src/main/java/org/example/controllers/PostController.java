package org.example.controllers;

import jakarta.validation.Valid;
import org.example.models.Post;
import org.example.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @GetMapping("/posts") // Список страниц
    public ResponseEntity<List<Post>> allPosts(@RequestParam(defaultValue = "10") Integer limit) {
        var result = PostService.getAllPosts(limit);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/posts/{id}") //Получить пост
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        var post = PostService.find(id);
        return ResponseEntity.of(post);
    }

    @PostMapping("/posts") // Создать пост
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        PostService.create(post);
        ResponseEntity.ok("все получилось");
        return ResponseEntity.status(201).body(post);
    }

    @PutMapping("/posts/{id}") // Обновить пост
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post data) {
        PostService.updatePost(id, data);
        return ResponseEntity.status(200).body(data);
    }

    @DeleteMapping("/posts/{id}") //Удалить
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        PostService.deletePost(id);
        return ResponseEntity.status(204).body(null);
    }
}
