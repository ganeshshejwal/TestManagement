package com.application.testmanagementapi.serviceTest;

import com.application.testmanagementapi.model.McqQuestionModel;
import com.application.testmanagementapi.model.SubCategoryModel;
import com.application.testmanagementapi.respository.McqQuestionRepository;
import com.application.testmanagementapi.service.McqQuestionService;
import com.application.testmanagementapi.service.SubcategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class McqQuestionServiceTests {

    @Mock
    private McqQuestionRepository mcqQuestionRepository;

    @Mock
    private SubcategoryService subcategoryService;

    @InjectMocks
    private McqQuestionService mcqQuestionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMcqQuestion() {
        McqQuestionModel mcqQuestion = createSampleMcqQuestion();
        when(mcqQuestionRepository.save(any(McqQuestionModel.class))).thenReturn(mcqQuestion);

        McqQuestionModel result = mcqQuestionService.saveMcqQuestion(mcqQuestion);

        assertEquals(mcqQuestion.getQuestionId(), result.getQuestionId());
        assertEquals(mcqQuestion.getQuestion(), result.getQuestion());
        assertEquals(mcqQuestion.getOptionOne(), result.getOptionOne());
        assertEquals(mcqQuestion.getOptionTwo(), result.getOptionTwo());
        assertEquals(mcqQuestion.getOptionThree(), result.getOptionThree());
        assertEquals(mcqQuestion.getOptionFour(), result.getOptionFour());
    }

    @Test
    public void testGetAllMcqQuestions() {
        List<McqQuestionModel> mcqQuestions = Arrays.asList(
                createSampleMcqQuestion(),
                createSampleMcqQuestion()
        );
        when(mcqQuestionRepository.findAll()).thenReturn(mcqQuestions);

        List<McqQuestionModel> retrievedMcqQuestions = mcqQuestionService.getAllMcqQuestions();

        assertEquals(2, retrievedMcqQuestions.size());
    }

    @Test
    public void testGetMcqQuestionById() {
        int questionId = 1;
        McqQuestionModel mcqQuestion = createSampleMcqQuestion();
        when(mcqQuestionRepository.findById(questionId)).thenReturn(Optional.of(mcqQuestion));

        Optional<McqQuestionModel> retrievedMcqQuestion = mcqQuestionService.getMcqQuestionById(questionId);

        assertEquals(questionId, retrievedMcqQuestion.get().getQuestionId());
        assertEquals(mcqQuestion.getQuestion(), retrievedMcqQuestion.get().getQuestion());
    }

    @Test
    public void testUpdateMcqQuestion() {
        int questionId = 1;
        McqQuestionModel mcqQuestionToUpdate = createSampleMcqQuestion();
        SubCategoryModel subCategory = createSampleSubCategory();
        when(subcategoryService.getSubcategoryById(anyInt())).thenReturn(Optional.of(subCategory));
        when(mcqQuestionRepository.save(any(McqQuestionModel.class))).thenReturn(mcqQuestionToUpdate);

        McqQuestionModel updatedMcqQuestion = mcqQuestionService.updateMcqQuestion(questionId, mcqQuestionToUpdate);

        assertEquals(questionId, updatedMcqQuestion.getQuestionId());
        assertEquals(mcqQuestionToUpdate.getQuestion(), updatedMcqQuestion.getQuestion());
        assertEquals(subCategory, updatedMcqQuestion.getSubcategoryId());
    }

    @Test
    public void testDeleteMcqQuestion() {
        int questionId = 1;

        doNothing().when(mcqQuestionRepository).deleteById(questionId);

        mcqQuestionService.deleteMcqQuestion(questionId);

        verify(mcqQuestionRepository, times(1)).deleteById(questionId);
    }

    private McqQuestionModel createSampleMcqQuestion() {
        McqQuestionModel mcqQuestion = new McqQuestionModel();
        mcqQuestion.setQuestionId(1);
        mcqQuestion.setQuestion("Sample Question");
        mcqQuestion.setOptionOne("Option A");
        mcqQuestion.setOptionTwo("Option B");
        mcqQuestion.setOptionThree("Option C");
        mcqQuestion.setOptionFour("Option D");
        mcqQuestion.setCorrectOption("Option A");
        mcqQuestion.setPositiveMark(1);
        mcqQuestion.setNegativeMark(0);
        mcqQuestion.setSubcategoryId(createSampleSubCategory());
        return mcqQuestion;
    }

    private SubCategoryModel createSampleSubCategory() {
        SubCategoryModel subCategory = new SubCategoryModel();
        subCategory.setSubcategoryId(1);
        subCategory.setSubcategoryName("Sample Subcategory");
        subCategory.setSubcategoryDescription("Sample Subcategory Description");
        return subCategory;
    }
}
