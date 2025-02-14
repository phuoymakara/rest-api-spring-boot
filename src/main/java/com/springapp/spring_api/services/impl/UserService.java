package com.springapp.spring_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springapp.spring_api.entities.User;
import com.springapp.spring_api.repositories.UserRepository;
import com.springapp.spring_api.services.InterfaceUserService;

@Service
public class UserService implements InterfaceUserService{
  
  @Autowired()
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserService(UserRepository userRepository){
    this.userRepository =  userRepository;
  }
  
  public List<User> getAllUsers(){
    return userRepository.findAll();
  }

  public User createUser(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Boolean checkEmail(String email){
    return userRepository.existsByEmail(email);
  }

  public Optional<User> findByID(Long id){
    return userRepository.findById(id);
  }

}
