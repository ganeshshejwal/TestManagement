package com.application.testmanagementapplication.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.application.testmanagementapplication.model.McqQuestion;
import com.application.testmanagementapplication.service.McqQuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/mcq-question")
public class McqQuestionController {

    private static final Logger logger = LoggerFactory.getLogger(McqQuestionController.class);


    @Autowired
    private McqQuestionService mcqQuestionService;

    @PostMapping
    public ResponseEntity<List<McqQuestion>> createMcqQuestion(@RequestParam("file") MultipartFile file) {
        logger.info("Request for creating McqQuestion");
        List<McqQuestion> createdMcqQuestion = mcqQuestionService.saveMcqQuestion(file);
        logger.info("McqQuestion created successfully");
        return new ResponseEntity<>(createdMcqQuestion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<McqQuestion>> getAllMcqQuestions() {
        logger.info("Request for fetching all McqQuestions");
        List<McqQuestion> mcqQuestions= mcqQuestionService.getAllMcqQuestions();
        logger.info("McqQuestions fetched successfully");
        return new ResponseEntity<>(mcqQuestions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<McqQuestion> getMcqQuestionById(@PathVariable("id") int id) {
        logger.info("Request for fetching McqQuestion using McqQuestion Id");
        Optional<McqQuestion> McqQuestion = mcqQuestionService.getMcqQuestionById(id);
        logger.info("McqQuestion fetched successfully");
        return new ResponseEntity<>(McqQuestion.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<McqQuestion> updateMcqQuestion(@PathVariable("id") int id, @RequestBody McqQuestion McqQuestion) {
        logger.info("Request for updating McqQuestion using SubCategory Id");
        McqQuestion updatedMcqQuestion = mcqQuestionService.updateMcqQuestion(id,McqQuestion);
        logger.info("McqQuestion updated sucessfully");
        return new ResponseEntity<>(updatedMcqQuestion, HttpStatus.OK);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMcqQuestion(@PathVariable("id") int id) {
        logger.info("Request for deleting McqQuestion using McqQuestion Id");
        mcqQuestionService.deleteMcqQuestion(id);
        logger.info("McqQuestion deleted sucessfully");
        return new ResponseEntity<>("McqQuestion deleted sucessfully", HttpStatus.NO_CONTENT);
    }
}
