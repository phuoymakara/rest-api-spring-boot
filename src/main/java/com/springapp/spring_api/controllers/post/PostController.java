package com.springapp.spring_api.controllers.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.spring_api.controllers.post.dto.PostDTO;
import com.springapp.spring_api.entities.Category;
import com.springapp.spring_api.entities.Post;
import com.springapp.spring_api.entities.User;
import com.springapp.spring_api.response.ApiResponse;
import com.springapp.spring_api.response.ValidationErrorUtil;
import com.springapp.spring_api.services.impl.CategoryServiceImpl;
import com.springapp.spring_api.services.impl.PostServiceImpl;
import com.springapp.spring_api.services.impl.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {
  private PostServiceImpl postImplService;
  private CategoryServiceImpl categoryImplService;
  private UserServiceImpl userServiceImpl;

  public PostController(
    PostServiceImpl postImplService, 
    CategoryServiceImpl categoryImplService,
    UserServiceImpl userServiceImpl
    ){
    this.postImplService = postImplService;
    this.categoryImplService = categoryImplService;
    this.userServiceImpl = userServiceImpl;
  }

  @GetMapping()
  public ResponseEntity<ApiResponse<List<Post>>> get() {
    try {
        List<Post> posts = postImplService.findAllWithCategoryAndPost();
        log.info("User Info",posts);
        return ResponseEntity.ok(new ApiResponse<List<Post>>(200, "Successfully retrieved posts", posts));
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<List<Post>>(500, e.getMessage(), null));
    }
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<?> create(@Valid @RequestBody PostDTO data, BindingResult result) {
    try {
        if(result.hasErrors()){
          return ResponseEntity.badRequest().body(ValidationErrorUtil.formatErrors(result));
        }

        Post post = new Post();
        Category cat = categoryImplService.findById(data.getCategoryId());
        User user = userServiceImpl.findByID(data.getUserId());
        post.setTitle(data.getTitle());
        post.setContent(data.getContent());
        post.setUser(user);
        post.setCategory(cat);

        Post ps = postImplService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "User created successfully!", ps));
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ApiResponse<>(500, e.getMessage(), null));
      }
  }

}
