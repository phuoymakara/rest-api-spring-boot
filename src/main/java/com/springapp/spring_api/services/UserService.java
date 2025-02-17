package com.springapp.spring_api.services;

import java.util.List;

import com.springapp.spring_api.entities.User;

public interface UserService {
  public List<User> getAllUsers();
  public User createUser(User user);
  public Boolean checkEmail(String email);
  public User findByID(Long id);
}
