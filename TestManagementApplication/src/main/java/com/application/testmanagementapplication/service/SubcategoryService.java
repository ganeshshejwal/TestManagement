package com.application.testmanagementapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.SubcategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public SubCategory saveSubcategory(SubCategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public List<SubCategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<SubCategory> getSubcategoryById(int id) {
        return subcategoryRepository.findById(id);
    }

    public SubCategory updateSubcategory(int id,SubCategory subcategory) {
        subcategory.setSubcategoryId(id);
        return subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(int id) {
        subcategoryRepository.deleteById(id);
    }
}
