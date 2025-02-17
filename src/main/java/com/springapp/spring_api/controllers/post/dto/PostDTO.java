package com.springapp.spring_api.controllers.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
  @NotBlank(message = "Title cannot be empty")
  private String title;

  @NotBlank(message = "Content cannot be empty")
  private String content;

  @NotNull(message = "Category ID is required")
  private Long categoryId;

  @NotNull(message = "User ID is required")
  private Long userId;
}



