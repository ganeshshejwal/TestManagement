package com.application.testmanagementapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapi.model.SubCategoryModel;
import com.application.testmanagementapi.respository.SubcategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public SubCategoryModel saveSubcategory(SubCategoryModel subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public List<SubCategoryModel> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<SubCategoryModel> getSubcategoryById(int id) {
        return subcategoryRepository.findById(id);
    }

    public SubCategoryModel updateSubcategory(int id,SubCategoryModel subcategory) {
        subcategory.setSubcategoryId(id);
        return subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(int id) {
        subcategoryRepository.deleteById(id);
    }
}
