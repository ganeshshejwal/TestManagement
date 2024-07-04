package com.application.testmanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.testmanagementapi.exception.CategoryNotFoundException;
import com.application.testmanagementapi.model.CategoryModel;
import com.application.testmanagementapi.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel category) {
        CategoryModel createdCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoryModel>> getCategoryById(@PathVariable("id") int id) {
        Optional<CategoryModel> category = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(category); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable("id") int id, @RequestBody CategoryModel category) {
        CategoryModel updatedCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id) {
        if(categoryService.getCategoryById(id).isPresent()) {
            categoryService.deleteCategory(id);
        }
        else {
            throw new CategoryNotFoundException("Category Id Not Found");
        }
        return ResponseEntity.noContent().build();
    }
}
