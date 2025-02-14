package com.springapp.spring_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springapp.spring_api.entities.User;
import com.springapp.spring_api.response.ApiResponse;
import com.springapp.spring_api.services.impl.UserService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@RestController()
@RequestMapping("/api/v1/users")
public class UserController {
  
  private static final Logger logger = LogManager.getLogger(UserController.class.getName());

  private UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
    logger.info("user controller");
  }


  @GetMapping()
  public ResponseEntity<ApiResponse<List<User>>> get() {
    try {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<List<User>>(200, "Successfully retrieved users", users));
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<List<User>>(500, e.getMessage(), null));
    }
}

  @PostMapping("/test")
  public ResponseEntity<?> test(@RequestBody String data){
    String txt = "Hello TXT"+data;
    return ResponseEntity.ok().body(txt);
  }
 
  @PostMapping(produces = "application/json")
  public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
    try {
        if (userService.checkEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, "User already exists!", null));
        }

        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "User created successfully!", createdUser));

      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ApiResponse<>(500, e.getMessage(), null));
      }
    }

}
