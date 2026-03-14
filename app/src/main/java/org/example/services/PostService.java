package org.example.services;

import org.example.models.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService {
    // Хранилище добавленных постов
    private static List<Post> posts = new ArrayList<Post>();


    public static Post create(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        posts.add(post);
        return post;
    }

    public static List<Post> getAllPosts(Integer limit) {
            return posts.stream().limit(limit).toList();
        }

    public static void deletePost(String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }

    public static Optional<Post> find(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public static Post updatePost(String id, Post data) {
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

}
