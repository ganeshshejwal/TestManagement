package com.application.testmanagementapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.application.testmanagementapplication.model.McqQuestion;

public interface McqQuestionService {

    public List<McqQuestion> createMcqQuestion(MultipartFile file);

    public List<McqQuestion> getAllMcqQuestions();

    public Optional<McqQuestion> getMcqQuestionById(int id);

    public McqQuestion updateMcqQuestion(int id,McqQuestion mcqQuestion);

    public void deleteMcqQuestion(int id);


    
}
