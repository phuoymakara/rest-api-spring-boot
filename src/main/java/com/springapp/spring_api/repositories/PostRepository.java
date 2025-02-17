package com.springapp.spring_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springapp.spring_api.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Custom query to get all posts with their categories and users
  @Query("SELECT p FROM Post p JOIN FETCH p.category c JOIN FETCH p.user u")
  List<Post> findAllWithCategoryAndUser();
}
