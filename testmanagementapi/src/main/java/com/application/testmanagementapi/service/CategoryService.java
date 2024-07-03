package com.application.testmanagementapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapi.model.CategoryModel;
import com.application.testmanagementapi.respository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel saveCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public CategoryModel updateCategory(int id, CategoryModel category) {
        category.setCategoryId(id);
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
