package com.springapp.spring_api.controllers.user;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springapp.spring_api.controllers.user.dto.UserDTO;
import com.springapp.spring_api.entities.User;
import com.springapp.spring_api.response.ApiResponse;
import com.springapp.spring_api.response.ValidationErrorUtil;
import com.springapp.spring_api.services.impl.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
  
  //private static final Logger logger = LogManager.getLogger(UserController.class.getName());
  private UserServiceImpl userService;

  public UserController(UserServiceImpl userService){
    this.userService = userService;
  }

  @GetMapping()
  public ResponseEntity<?> get() {
    try {
        List<User> users = userService.getAllUsers();
        log.info("user data =>",users);
        return ResponseEntity.ok(new ApiResponse<List<User>>(200, "Successfully retrieved users", users));
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<List<User>>(500, e.getMessage(), null));
    }
  }
 
  @PostMapping(produces = "application/json")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO data, BindingResult result) {
    try {
        if(result.hasErrors()){
          return ResponseEntity.badRequest().body(ValidationErrorUtil.formatErrors(result));
        }
        if (userService.checkEmail(data.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, "User already exists!", null));
        }
        User user = new User();
        user.setEmail(data.getEmail());
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "User created successfully!", createdUser));
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ApiResponse<>(500, e.getMessage(), null));
      }
  }

    //Test validation
    @PostMapping(value = "/test", produces = "application/json")
    public ResponseEntity<?> test(@Valid @RequestBody UserDTO data, BindingResult result){
      if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(ValidationErrorUtil.formatErrors(result));
      }
      return ResponseEntity.ok("User created successfully");
    }

}
