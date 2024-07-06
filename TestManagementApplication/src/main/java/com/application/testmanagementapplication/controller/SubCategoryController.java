package com.application.testmanagementapplication.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.service.SubCategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/subcategory")
public class SubCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory) {
        logger.info("Request for creating SubCategory");
        SubCategory createdSubcategory = subCategoryService.createSubCategory(subCategory);
        logger.info("SubCategory created successfully");
        return new ResponseEntity<>(createdSubcategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        logger.info("Request for fetching all SubCategories");
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        logger.info("SubCategories fetched successfully");
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("id") int id) {
        logger.info("Request for fetching SubCategory using SubCategory Id");
        Optional<SubCategory> subCategory = subCategoryService.getSubCategoryById(id);
        logger.info("SubCategory fetched successfully");
        return new ResponseEntity<>(subCategory.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable("id") int id, @RequestBody SubCategory subCategory) {
        logger.info("Request for updating SubCategory using SubCategory Id");
        SubCategory updatedSubcategory = subCategoryService.updateSubCategory(id,subCategory);
        logger.info("SubCategory updated sucessfully");
        return new ResponseEntity<>(updatedSubcategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("id") int id) {
        logger.info("Request for deleting SubCategory using SubCategory Id");
        subCategoryService.deleteSubCategory(id);
        logger.info("SubCategory deleted sucessfully");
        return new ResponseEntity<>("Category deleted sucessfully", HttpStatus.NO_CONTENT);
    }
}
