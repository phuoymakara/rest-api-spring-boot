package com.springapp.spring_api.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springapp.spring_api.entities.User;
import com.springapp.spring_api.repositories.UserRepository;
import com.springapp.spring_api.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
  
  @Autowired()
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserServiceImpl(UserRepository userRepository){
    this.userRepository =  userRepository;
  }
  
  public List<User> getAllUsers(){
    log.debug("Debug from user service");
    return userRepository.findAll();
  }

  public User createUser(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Boolean checkEmail(String email){
    return userRepository.existsByEmail(email);
  }

  public User findByID(Long id){
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

}
