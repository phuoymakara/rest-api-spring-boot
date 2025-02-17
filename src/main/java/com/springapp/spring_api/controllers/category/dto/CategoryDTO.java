package com.springapp.spring_api.controllers.category.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
  @NotNull(message = "Name cannot be null")
  @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
  private String name;
}
