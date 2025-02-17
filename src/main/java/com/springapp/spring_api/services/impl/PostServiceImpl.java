package com.springapp.spring_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springapp.spring_api.entities.Post;
import com.springapp.spring_api.repositories.PostRepository;
import com.springapp.spring_api.services.PostService;

@Service
public class PostServiceImpl implements PostService{


  private PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository){
    this.postRepository = postRepository;
  }

  @Override
  public List<Post> findAllWithCategoryAndPost() {
    return this.postRepository.findAllWithCategoryAndUser();
  }


  public List<Post> getAll(){
    return this.postRepository.findAll();
  }

  @Override
  public Post createPost(Post post) {
    return this.postRepository.save(post);
  }
  
  @Override
  public Post findByID() {
    return null;
  }
}
