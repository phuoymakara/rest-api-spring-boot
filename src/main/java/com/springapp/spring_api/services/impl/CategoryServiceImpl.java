package com.springapp.spring_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.spring_api.entities.Category;
import com.springapp.spring_api.repositories.CategoryRepository;
import com.springapp.spring_api.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

  @Autowired
  private CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository){
    this.categoryRepository = categoryRepository;
  }

  public Category create(Category category) {
    return this.categoryRepository.save(category);
  }

  public Category findById(Long id) {
    return this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public List<Category> getAll() {
    return this.categoryRepository.findAll();
  }

  public Boolean checkName(String name) {
    return this.categoryRepository.existsName(name);
  }

}
