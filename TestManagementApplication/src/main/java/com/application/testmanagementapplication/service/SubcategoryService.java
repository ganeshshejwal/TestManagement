package com.application.testmanagementapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public SubCategory saveSubcategory(SubCategory subcategory) {
        return subCategoryRepository.save(subcategory);
    }

    public List<SubCategory> getAllSubcategories() {
        return subCategoryRepository.findAll();
    }

    public Optional<SubCategory> getSubcategoryById(int id) {
        return subCategoryRepository.findById(id);
    }

    public SubCategory updateSubcategory(int id,SubCategory subcategory) {
        subcategory.setSubCategoryId(id);
        return subCategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(int id) {
        subCategoryRepository.deleteById(id);
    }
}
