package com.application.testmanagementapplication.service;

import java.util.List;
import java.util.Optional;

import com.application.testmanagementapplication.model.Category;

public interface CategoryService {
    
    public Category createCategory(Category category);

    public List<Category> getAllCategories();

    public Optional<Category> getCategoryById(int id);

    public Category updateCategory(int id, Category category);

    public void deleteCategory(int id);
}
