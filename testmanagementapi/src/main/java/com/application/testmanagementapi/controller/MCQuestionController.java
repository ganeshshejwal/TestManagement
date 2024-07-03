package com.application.testmanagementapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.testmanagementapi.model.MCQuestion;
import com.application.testmanagementapi.service.MCQuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp")
public class MCQuestionController {

    private static final Logger logger=LoggerFactory.getLogger(MCQuestionController.class);
    
    @Autowired
    private MCQuestionService questionService;

    @PostMapping
    public ResponseEntity<MCQuestion> createQuestion(@RequestBody MCQuestion question) {
        logger.info("Question Created Sucessfully");
        MCQuestion createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MCQuestion>> getAllQuestions() {
        logger.info("All Questions Fetched Sucessfully");
        List<MCQuestion> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MCQuestion> getQuestionById(@PathVariable("id") Long id) {
        logger.info("Question Fetched With Id Sucessfully");
        Optional<MCQuestion> question = questionService.getQuestionById(id);
        return new ResponseEntity<>(question.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MCQuestion> updateQuestion(@PathVariable("id") Long id,@RequestBody MCQuestion question) {
        MCQuestion updatedQuestion = questionService.updateQuestion(id, question);
        if (updatedQuestion != null) {
            logger.info("Question Updated Sucessfully");
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            logger.warn("Question Not Found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
        logger.info("Question Deleted Sucessfully");
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
