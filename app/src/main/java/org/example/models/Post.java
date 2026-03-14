package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @NotBlank(message = "Title не может быть пустым")
    @Size(min = 3, max = 100, message = "Title должен быть от 3 до 100 символов")
    private String title;

    @NotBlank(message = "Content не может быть пустым")
    @Size(min = 10, message = "Content должен быть минимум 10 символов")
    private String content;

    private String author;
    private LocalDateTime createdAt;
    private Long id;

}