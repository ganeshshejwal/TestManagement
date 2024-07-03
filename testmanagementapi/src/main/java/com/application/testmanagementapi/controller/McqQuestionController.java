package com.application.testmanagementapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.testmanagementapi.model.McqQuestionModel;
import com.application.testmanagementapi.service.McqQuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testapp/mcqquestion")
public class McqQuestionController {

    @Autowired
    private McqQuestionService McqQuestionService;

    @PostMapping
    public ResponseEntity<McqQuestionModel> createMcqQuestion(@RequestBody McqQuestionModel McqQuestion) {
        McqQuestionModel createdMcqQuestion = McqQuestionService.saveMcqQuestion(McqQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMcqQuestion);
    }

    @GetMapping
    public ResponseEntity<List<McqQuestionModel>> getAllMcqQuestions() {
        List<McqQuestionModel> questions= McqQuestionService.getAllMcqQuestions();
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<McqQuestionModel>> getMcqQuestionById(@PathVariable("id") int id) {
        Optional<McqQuestionModel> McqQuestion = McqQuestionService.getMcqQuestionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(McqQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<McqQuestionModel> updateMcqQuestion(@PathVariable("id") int id, @RequestBody McqQuestionModel McqQuestion) {
        McqQuestionModel updatedMcqQuestion = McqQuestionService.updateMcqQuestion(id,McqQuestion);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMcqQuestion);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMcqQuestion(@PathVariable("id") int id) {
        McqQuestionService.deleteMcqQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
