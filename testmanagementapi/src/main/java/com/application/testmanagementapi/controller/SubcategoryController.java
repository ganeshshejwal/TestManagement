package com.application.testmanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.testmanagementapi.exception.QuestionNotFoundException;
import com.application.testmanagementapi.model.SubCategoryModel;
import com.application.testmanagementapi.service.SubcategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @PostMapping
    public ResponseEntity<SubCategoryModel> createSubcategory(@RequestBody SubCategoryModel subcategory) {
        SubCategoryModel createdSubcategory = subcategoryService.saveSubcategory(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubcategory);
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryModel>> getAllSubcategories() {
        List<SubCategoryModel> categories = subcategoryService.getAllSubcategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SubCategoryModel>> getSubcategoryById(@PathVariable("id") int id) {
        Optional<SubCategoryModel> subcategory = subcategoryService.getSubcategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryModel> updateSubcategory(@PathVariable("id") int id, @RequestBody SubCategoryModel subcategory) {
        SubCategoryModel updatedSubcategory = subcategoryService.saveSubcategory(subcategory);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSubcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable("id") int id) {
        if(subcategoryService.getSubcategoryById(id).isPresent()){
            subcategoryService.deleteSubcategory(id);
        }
        else{
            throw new QuestionNotFoundException("Subcategory Not Found For Given Id");
        }
        return ResponseEntity.noContent().build();
    }
}
