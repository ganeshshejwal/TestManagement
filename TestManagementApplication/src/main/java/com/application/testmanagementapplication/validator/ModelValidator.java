package com.application.testmanagementapplication.validator;

import org.springframework.stereotype.Component;

import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.SubCategory;

@Component
public class ModelValidator {

    public boolean isCategoryValid(Category category){
        return category == null  &&
               category.getCategoryName() == null &&
               category.getCategoryName().isEmpty() &&
               category.getCategoryDescription() == null &&
               category.getCategoryDescription().isEmpty();
    }

    public boolean isSubCategoryValid(SubCategory subCategory){
        return subCategory == null &&
           subCategory.getCategory() == null &&
           subCategory.getSubCategoryName() == null &&
           subCategory.getSubCategoryName().isEmpty() &&
           subCategory.getSubCategoryDescription() == null &&
           subCategory.getSubCategoryDescription().isEmpty();
    }

}
