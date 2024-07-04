package com.application.testmanagementapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.application.testmanagementapi.exception.QuestionNotFoundException;
import com.application.testmanagementapi.model.McqQuestionModel;
import com.application.testmanagementapi.service.McqQuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/mcqquestion")
public class McqQuestionController {

    @Autowired
    private McqQuestionService mcqQuestionService;

    @PostMapping
    public ResponseEntity<List<McqQuestionModel>> createMcqQuestion(@RequestParam("file") MultipartFile file) {
        List<McqQuestionModel> createdMcqQuestion = mcqQuestionService.saveMcqQuestion(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMcqQuestion);
    }

    @GetMapping
    public ResponseEntity<List<McqQuestionModel>> getAllMcqQuestions() {
        List<McqQuestionModel> questions= mcqQuestionService.getAllMcqQuestions();
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<McqQuestionModel>> getMcqQuestionById(@PathVariable("id") int id) {
        Optional<McqQuestionModel> McqQuestion = mcqQuestionService.getMcqQuestionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(McqQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<McqQuestionModel> updateMcqQuestion(@PathVariable("id") int id, @RequestBody McqQuestionModel McqQuestion) {
        McqQuestionModel updatedMcqQuestion = mcqQuestionService.updateMcqQuestion(id,McqQuestion);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMcqQuestion);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMcqQuestion(@PathVariable("id") int id) {
        if(mcqQuestionService.getMcqQuestionById(id).isPresent()){
            mcqQuestionService.deleteMcqQuestion(id);
        }
        else{
            throw new QuestionNotFoundException("Question Not Found For Given Id");
        }
        return ResponseEntity.noContent().build();
    }
}
