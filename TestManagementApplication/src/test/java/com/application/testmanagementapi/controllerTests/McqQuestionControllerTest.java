package com.application.testmanagementapi.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.application.testmanagementapplication.controller.McqQuestionController;
import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.McqQuestion;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.service.McqQuestionService;

public class McqQuestionControllerTest {

    @Mock
    McqQuestion mcqQuestion;

    @Mock 
    SubCategory subCategory;

    @Mock
    Category category;

    @Mock
    private McqQuestionService mcqQuestionService;

    @InjectMocks
    private McqQuestionController mcqQuestionController;

    @BeforeEach
    public void setup() {
        category = new Category(1, "Java", "Collection");
        subCategory = new SubCategory(1,category,"Annotation","Annotation in spring");
        mcqQuestion = new McqQuestion(1, "What is Spring Boot?", "A Java framework", "A Spring module", "A Spring project", "An annotation", "A Spring project", "3", "-1",subCategory);
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    public void testCreateMcqQuestion() throws Exception {
        InputStream inputStream = new FileInputStream("C:\\Users\\ganesh.shejwal\\Downloads\\QuestionBank.xlsx");
        MockMultipartFile file = new MockMultipartFile("file", "QuestionBank.xlsx", "text/xlsx", inputStream);
        List<McqQuestion> mcqQuestions = new ArrayList<>();
        when(mcqQuestionService.createMcqQuestion(file)).thenReturn(mcqQuestions);
        ResponseEntity<List<McqQuestion>> createdMcqQuestions= mcqQuestionController.createMcqQuestion(file);
        assertEquals(HttpStatus.CREATED, createdMcqQuestions.getStatusCode());
    }

    @Test
    public void testGetAllMcqQuestions() {
        List<McqQuestion> mcqQuestions = new ArrayList<>();
        mcqQuestions.add(mcqQuestion);
        when(mcqQuestionService.getAllMcqQuestions()).thenReturn(mcqQuestions);
        ResponseEntity<List<McqQuestion>> responseEntity = mcqQuestionController.getAllMcqQuestions();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetMcqQuestionById() {
        int mcqQuestionId = 1;
        when(mcqQuestionService.getMcqQuestionById(mcqQuestionId)).thenReturn(Optional.of(mcqQuestion));
        ResponseEntity<McqQuestion> responseEntity = mcqQuestionController.getMcqQuestionById(mcqQuestionId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testUpdateMcqQuestion() {
        int mcqQuestionId = 1;
        when(mcqQuestionService.updateMcqQuestion(mcqQuestionId, mcqQuestion)).thenReturn(mcqQuestion);
        ResponseEntity<McqQuestion> responseEntity = mcqQuestionController.updateMcqQuestion(mcqQuestionId, mcqQuestion);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testDeleteMcqQuestion() {
        int mcqQuestionId = 1;
        mcqQuestionService.deleteMcqQuestion(mcqQuestionId);
        ResponseEntity<String> responseEntity = mcqQuestionController.deleteMcqQuestion(mcqQuestionId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    
}
