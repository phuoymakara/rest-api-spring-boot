package com.springapp.spring_api.services;

import java.util.List;
import com.springapp.spring_api.entities.Post;

public interface PostService {
  public List<Post> getAll();
  public Post createPost(Post post);
  public Post findByID();
  public List<Post> findAllWithCategoryAndPost();
}
