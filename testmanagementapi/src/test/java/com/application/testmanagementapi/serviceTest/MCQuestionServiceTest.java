package com.application.testmanagementapi.serviceTest;

import com.application.testmanagementapi.model.MCQuestion;
import com.application.testmanagementapi.repository.MCQuestionRepository;
import com.application.testmanagementapi.service.MCQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MCQuestionServiceTest {

    @Mock
    MCQuestion question;

    @Mock
    private MCQuestionRepository repository;

    @InjectMocks
    private MCQuestionService service;

    @BeforeEach
    public void setUp() {
        question = new MCQuestion(1L, "SpringBoot", "Option 1", "Option 2", "Option 3", "Option 4", "Option 1", "1", "0.25");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateQuestion() {
        when(repository.save(question)).thenReturn(question);
        MCQuestion savedQuestion = service.createQuestion(question);
        assertEquals(question, savedQuestion);
    }

    @Test
    public void testGetAllQuestions() {
        List<MCQuestion> questionList = new ArrayList<>();
        questionList.add(question);
        questionList.add(question);
        when(repository.findAll()).thenReturn(questionList);
        List<MCQuestion> fetchedList = service.getAllQuestions();
        assertEquals(questionList, fetchedList);
    }

    @Test
    public void testGetQuestionById() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(question));
        Optional<MCQuestion> fetchedQuestion = service.getQuestionById(id);
        assertEquals(Optional.of(question), fetchedQuestion);
    }

    @Test
    public void testDeleteQuestion() {
        Long id = 1L;
        service.deleteQuestion(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateQuestion() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(question));
        when(repository.save(question)).thenReturn(question);
        MCQuestion updatedQuestion = service.updateQuestion(id, question);
        assertEquals(question, updatedQuestion);
    }


}
