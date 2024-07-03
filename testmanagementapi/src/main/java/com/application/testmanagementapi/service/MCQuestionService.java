package com.application.testmanagementapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapi.model.MCQuestion;
import com.application.testmanagementapi.repository.MCQuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MCQuestionService {

    @Autowired
    private MCQuestionRepository questionRepository;

    public MCQuestion createQuestion(MCQuestion question) {
        return questionRepository.save(question);
    }

    public List<MCQuestion> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<MCQuestion> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public MCQuestion updateQuestion(Long id, MCQuestion question) {
        Optional<MCQuestion> questionOptional = getQuestionById(id);
        if (questionOptional.isPresent()) {
            question.setQuestionId(id);
            return questionRepository.save(question);
        }
        return null; 
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
