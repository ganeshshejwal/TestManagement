package com.application.testmanagementapplication.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.service.CategoryService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        logger.info("Request for creating Category");
        Category createdCategory = categoryService.saveCategory(category);
        logger.info("Category created successfully");
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("Request for fetching all Categories");
        List<Category> categories = categoryService.getAllCategories();
        logger.info("Categories fetched successfully");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        logger.info("Request for fetching Category using Category Id");
        Optional<Category> category = categoryService.getCategoryById(id);
        logger.info("Category fetched successfully");
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        logger.info("Request for updating Category using Category Id");
        Category updatedCategory = categoryService.updateCategory(id,category);
        logger.info("Category updated sucessfully");
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        logger.info("Request for deleting Category using Category Id");
        categoryService.deleteCategory(id);
        logger.info("Category deleted sucessfully");
        return new ResponseEntity<>("Category deleted sucessfully" ,HttpStatus.NO_CONTENT);
    }
}
