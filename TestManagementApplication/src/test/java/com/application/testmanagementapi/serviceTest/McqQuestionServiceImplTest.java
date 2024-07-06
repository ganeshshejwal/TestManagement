package com.application.testmanagementapi.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.McqQuestion;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.McqQuestionRepository;
import com.application.testmanagementapplication.respository.SubCategoryRepository;
import com.application.testmanagementapplication.service.serviceimpl.McqQuestionServiceImpl;

public class McqQuestionServiceImplTest {

    @Mock
    McqQuestion mcqQuestion;

    @Mock
    SubCategory subCategory;

    @Mock 
    Category category;

    @Mock
    private McqQuestionRepository mcqQuestionRepository;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @InjectMocks
    private McqQuestionServiceImpl mcqQuestionService;


    @BeforeEach
    public void setup() {
        category = new Category(1, "Java", "Collection");
        subCategory = new SubCategory(1, category, "Annotation", "Annotation in spring");
        mcqQuestion = new McqQuestion(1, "What is Spring Boot?", "A Java framework", "A Spring module", "A Spring project", "An annotation", "A Spring project", "3", "-1", subCategory);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMcqQuestion_Success() throws Exception {
        InputStream inputStream = new FileInputStream("C:\\Users\\ganesh.shejwal\\Downloads\\QuestionBank.xlsx");
        MockMultipartFile file = new MockMultipartFile("file", "QuestionBank.xlsx", "text/xlsx", inputStream);
        List<McqQuestion> mcqQuestions = new ArrayList<>();
        when(subCategoryRepository.findBysubCategoryName(any())).thenReturn(Optional.of(subCategory));
        when(mcqQuestionRepository.findByquestion(any())).thenReturn(Optional.empty());
        when(mcqQuestionRepository.save(any())).thenReturn(mcqQuestion);
        List<McqQuestion> createdMcqQuestions = mcqQuestionService.createMcqQuestion(file);
        assertEquals(mcqQuestions.size(), createdMcqQuestions.size());
        assertEquals(HttpStatus.CREATED, ResponseEntity.ok(createdMcqQuestions).getStatusCode());
    }

    @Test
    public void testGetAllMcqQuestions() {
        List<McqQuestion> mcqQuestions = new ArrayList<>();
        mcqQuestions.add(mcqQuestion);
        when(mcqQuestionRepository.findAll()).thenReturn(mcqQuestions);

        List<McqQuestion> fetchedMcqQuestions = mcqQuestionService.getAllMcqQuestions();

        assertEquals(mcqQuestions.size(), fetchedMcqQuestions.size());
        assertEquals(mcqQuestions.get(0).getQuestionId(), fetchedMcqQuestions.get(0).getQuestionId());
        assertEquals(HttpStatus.OK, ResponseEntity.ok(fetchedMcqQuestions).getStatusCode());
    }

    @Test
    public void testGetMcqQuestionById() {
        int mcqQuestionId = 1;
        when(mcqQuestionRepository.findById(mcqQuestionId)).thenReturn(Optional.of(mcqQuestion));

        Optional<McqQuestion> fetchedMcqQuestion = mcqQuestionService.getMcqQuestionById(mcqQuestionId);

        assertEquals(mcqQuestionId, fetchedMcqQuestion.get().getQuestionId());
        assertEquals(mcqQuestion.getQuestion(), fetchedMcqQuestion.get().getQuestion());
        assertEquals(HttpStatus.OK, ResponseEntity.ok(fetchedMcqQuestion.get()).getStatusCode());
    }

    @Test
    public void testUpdateMcqQuestion() {
        int mcqQuestionId = 1;
        McqQuestion updatedMcqQuestion = new McqQuestion(mcqQuestionId, "Updated question?", "Option 1", "Option 2", "Option 3", "Option 4", "Option 1", "1", "-1", subCategory);
        when(mcqQuestionRepository.findById(mcqQuestionId)).thenReturn(Optional.of(mcqQuestion));
        when(mcqQuestionRepository.save(updatedMcqQuestion)).thenReturn(updatedMcqQuestion);

        McqQuestion result = mcqQuestionService.updateMcqQuestion(mcqQuestionId, updatedMcqQuestion);

        assertEquals(updatedMcqQuestion.getQuestion(), result.getQuestion());
        assertEquals(HttpStatus.OK, ResponseEntity.ok(result).getStatusCode());
    }

    @Test
    public void testDeleteMcqQuestion() {
        int mcqQuestionId = 1;
        when(mcqQuestionRepository.findById(mcqQuestionId)).thenReturn(Optional.of(mcqQuestion));

        mcqQuestionService.deleteMcqQuestion(mcqQuestionId);

        assertEquals(HttpStatus.NO_CONTENT, ResponseEntity.noContent().build().getStatusCode());
    }

}
