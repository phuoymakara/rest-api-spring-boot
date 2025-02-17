package com.springapp.spring_api.services;

import java.util.List;

import com.springapp.spring_api.entities.Category;

public interface CategoryService {
  public List<Category> getAll();
  public Category findById(Long id);
  public Category create(Category category);
  public Boolean checkName(String name);
}
