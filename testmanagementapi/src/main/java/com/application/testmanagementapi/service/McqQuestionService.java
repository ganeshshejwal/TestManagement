package com.application.testmanagementapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapi.model.McqQuestionModel;
import com.application.testmanagementapi.model.SubCategoryModel;
import com.application.testmanagementapi.respository.McqQuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class McqQuestionService {

    @Autowired
    private McqQuestionRepository mcqQuestionRepository;

    @Autowired
    private SubcategoryService  subcategoryService;
    
    public McqQuestionModel saveMcqQuestion(McqQuestionModel McqQuestion) {
        return mcqQuestionRepository.save(McqQuestion);
    }

    public List<McqQuestionModel> getAllMcqQuestions() {
        return mcqQuestionRepository.findAll();
    }

    public Optional<McqQuestionModel> getMcqQuestionById(int id) {
        return mcqQuestionRepository.findById(id);
    }

    public McqQuestionModel updateMcqQuestion(int id,McqQuestionModel mcqQuestion) {
        Optional<SubCategoryModel> subCategory = subcategoryService.getSubcategoryById(id);
        if(subCategory.isPresent()) mcqQuestion.setSubcategoryId(subCategory.get());
        return mcqQuestionRepository.save(mcqQuestion);
    }

    public void deleteMcqQuestion(int id) {
        mcqQuestionRepository.deleteById(id);
    }
}
