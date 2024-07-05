package com.application.testmanagementapi.serviceTest;

import com.application.testmanagementapi.model.CategoryModel;
import com.application.testmanagementapi.model.SubCategoryModel;
import com.application.testmanagementapi.respository.SubcategoryRepository;
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

public class SubcategoryServiceTests {

    @Mock
    private SubcategoryRepository subcategoryRepository;

    @InjectMocks
    private SubcategoryService subcategoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSubcategory() {
        SubCategoryModel subcategory = createSampleSubcategory();
        when(subcategoryRepository.save(any(SubCategoryModel.class))).thenReturn(subcategory);

        SubCategoryModel result = subcategoryService.saveSubcategory(subcategory);

        assertEquals(subcategory.getSubcategoryId(), result.getSubcategoryId());
        assertEquals(subcategory.getSubcategoryName(), result.getSubcategoryName());
        assertEquals(subcategory.getSubcategoryDescription(), result.getSubcategoryDescription());
    }

    @Test
    public void testGetAllSubcategories() {
        List<SubCategoryModel> subcategories = Arrays.asList(
                createSampleSubcategory(),
                createSampleSubcategory()
        );
        when(subcategoryRepository.findAll()).thenReturn(subcategories);

        List<SubCategoryModel> retrievedSubcategories = subcategoryService.getAllSubcategories();

        assertEquals(2, retrievedSubcategories.size());
    }

    @Test
    public void testGetSubcategoryById() {
        int subcategoryId = 1;
        SubCategoryModel subcategory = createSampleSubcategory();
        when(subcategoryRepository.findById(subcategoryId)).thenReturn(Optional.of(subcategory));

        Optional<SubCategoryModel> retrievedSubcategory = subcategoryService.getSubcategoryById(subcategoryId);

        assertEquals(subcategoryId, retrievedSubcategory.get().getSubcategoryId());
        assertEquals(subcategory.getSubcategoryName(), retrievedSubcategory.get().getSubcategoryName());
        assertEquals(subcategory.getSubcategoryDescription(), retrievedSubcategory.get().getSubcategoryDescription());
    }

    @Test
    public void testUpdateSubcategory() {
        int subcategoryId = 1;
        SubCategoryModel subcategoryToUpdate = createSampleSubcategory();
        when(subcategoryRepository.save(any(SubCategoryModel.class))).thenReturn(subcategoryToUpdate);

        SubCategoryModel updatedSubcategory = subcategoryService.updateSubcategory(subcategoryId, subcategoryToUpdate);

        assertEquals(subcategoryId, updatedSubcategory.getSubcategoryId());
        assertEquals(subcategoryToUpdate.getSubcategoryName(), updatedSubcategory.getSubcategoryName());
        assertEquals(subcategoryToUpdate.getSubcategoryDescription(), updatedSubcategory.getSubcategoryDescription());
    }

    @Test
    public void testDeleteSubcategory() {
        int subcategoryId = 1;

        doNothing().when(subcategoryRepository).deleteById(subcategoryId);

        subcategoryService.deleteSubcategory(subcategoryId);

        verify(subcategoryRepository, times(1)).deleteById(subcategoryId);
    }

    private SubCategoryModel createSampleSubcategory() {
        SubCategoryModel subcategory = new SubCategoryModel();
        subcategory.setSubcategoryId(1);
        subcategory.setSubcategoryName("Sample Subcategory");
        subcategory.setSubcategoryDescription("Sample Subcategory Description");
        subcategory.setCategoryModel(new CategoryModel()); 
        return subcategory;
    }
}
