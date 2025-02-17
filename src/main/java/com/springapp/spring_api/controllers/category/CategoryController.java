package com.springapp.spring_api.controllers.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.spring_api.controllers.category.dto.CategoryDTO;
import com.springapp.spring_api.entities.Category;
import com.springapp.spring_api.response.ApiResponse;
import com.springapp.spring_api.response.ValidationErrorUtil;
import com.springapp.spring_api.services.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/v1/categories")
public class CategoryController {
  
  private CategoryServiceImpl categoryImplService;

  public CategoryController(CategoryServiceImpl categoryImplService){
    this.categoryImplService = categoryImplService;
  }

  @GetMapping()
  public ResponseEntity<ApiResponse<List<Category>>> get() {
      try {
          List<Category> cat = categoryImplService.getAll();
          return ResponseEntity.ok(new ApiResponse<List<Category>>(200, "Successfully retrieved categories", cat));
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ApiResponse<List<Category>>(500, e.getMessage(), null));
      }
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<?> createUser(@Valid @RequestBody CategoryDTO data, BindingResult result) {
    try {
        if(result.hasErrors()){
          return ResponseEntity.badRequest().body(ValidationErrorUtil.formatErrors(result));
        }
        if (categoryImplService.checkName(data.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, "Category already exists!", null));
        }
        Category cat = new Category();
        cat.setName(data.getName());
        Category catRes = categoryImplService.create(cat);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "User created successfully!", catRes));
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ApiResponse<>(500, e.getMessage(), null));
      }
  }

}
