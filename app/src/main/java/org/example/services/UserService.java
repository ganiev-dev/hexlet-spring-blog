package org.example.services;

import jakarta.validation.Valid;
import org.example.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    // Хранилище добавленных пользователей
    private static List<User> users = new ArrayList<User>();


    public static User createUser(@Valid User user) {

        users.add(user);
        return user;
    }

    public static List<User> getAllUsers(Integer limit) {
        return users.stream().limit(limit).toList();
    }

    public static Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public static User updateUser(Long id, User data) {
        var maybePage = users.stream()
                .filter(user -> user.getId() != null && user.getId().equals(id))
                .findFirst();
        if (maybePage.isPresent()) {
            var page = maybePage.get();
            page.setName(data.getName());
            page.setEmail(data.getEmail());
        }
        return data;
    }

    public static void deleteUser(Long id) {
        users.removeIf(p -> p.getId().equals(id));
    }
}
