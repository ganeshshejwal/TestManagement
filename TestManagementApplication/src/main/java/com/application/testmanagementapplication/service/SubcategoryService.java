package com.application.testmanagementapplication.service;

import java.util.List;
import java.util.Optional;

import com.application.testmanagementapplication.model.SubCategory;

public interface SubCategoryService {
    
    public SubCategory createSubCategory(SubCategory subCategory);

    public List<SubCategory> getAllSubCategories();

    public Optional<SubCategory> getSubCategoryById(int id);

    public SubCategory updateSubCategory(int id,SubCategory subCategory);

    public void deleteSubCategory(int id);
}
