package com.application.testmanagementapplication.validator;

import org.springframework.stereotype.Component;

import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.McqQuestion;
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

    public boolean isMcqQuestionValid(McqQuestion mcqQuestion) {
        return mcqQuestion != null &&
           isNotEmpty(mcqQuestion.getQuestion()) &&
           isNotEmpty(mcqQuestion.getOptionOne()) &&
           isNotEmpty(mcqQuestion.getOptionTwo()) &&
           isNotEmpty(mcqQuestion.getOptionThree()) &&
           isNotEmpty(mcqQuestion.getOptionFour()) &&
           isNotEmpty(mcqQuestion.getCorrectOption()) &&
           isNotEmpty(mcqQuestion.getPositiveMark()) &&
           isNotEmpty(mcqQuestion.getNegativeMark()) &&
           mcqQuestion.getSubCategory() != null;
    }

    private boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

}
