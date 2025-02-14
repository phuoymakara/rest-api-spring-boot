package com.springapp.spring_api.services;

import java.util.List;
import java.util.Optional;

import com.springapp.spring_api.entities.User;

public interface InterfaceUserService {
  public List<User> getAllUsers();
  public User createUser(User user);
  public Boolean checkEmail(String email);
  public Optional<User> findByID(Long id);
}
