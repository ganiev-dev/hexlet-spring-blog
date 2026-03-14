package org.example.controllers;

import jakarta.validation.Valid;
import org.example.models.User;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        UserService.createUser(user);
        ResponseEntity.ok("Usee created successful");
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/users") // Список страниц
    public ResponseEntity<List<User>> allUsers(@RequestParam(defaultValue = "10") Integer limit) {
        var result = UserService.getAllUsers(limit);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/users/{id}") //Получить пост
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        var user = UserService.findUserById(id);
        return ResponseEntity.of(user);
    }

    @PutMapping("/users/{id}") // Обновить пост
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User data) {
        UserService.updateUser(id, data);
        return ResponseEntity.status(200).body(data);
    }

    @DeleteMapping("/users/{id}") //Удалить
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserService.deleteUser(id);
        return ResponseEntity.status(204).body(null);
    }
}
