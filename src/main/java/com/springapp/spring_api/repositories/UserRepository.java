package com.springapp.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springapp.spring_api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  //Continue for custom function queries;

  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = :email")
  boolean existsByEmail(String email);
} 