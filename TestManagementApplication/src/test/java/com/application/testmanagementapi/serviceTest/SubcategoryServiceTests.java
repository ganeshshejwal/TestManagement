package com.application.testmanagementapi.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.SubcategoryRepository;
import com.application.testmanagementapplication.service.SubcategoryService;

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
        SubCategory subcategory = createSampleSubcategory();
        when(subcategoryRepository.save(any(SubCategory.class))).thenReturn(subcategory);

        SubCategory result = subcategoryService.saveSubcategory(subcategory);

        assertEquals(subcategory.getSubcategoryId(), result.getSubcategoryId());
        assertEquals(subcategory.getSubcategoryName(), result.getSubcategoryName());
        assertEquals(subcategory.getSubcategoryDescription(), result.getSubcategoryDescription());
    }

    @Test
    public void testGetAllSubcategories() {
        List<SubCategory> subcategories = Arrays.asList(
                createSampleSubcategory(),
                createSampleSubcategory()
        );
        when(subcategoryRepository.findAll()).thenReturn(subcategories);

        List<SubCategory> retrievedSubcategories = subcategoryService.getAllSubcategories();

        assertEquals(2, retrievedSubcategories.size());
    }

    @Test
    public void testGetSubcategoryById() {
        int subcategoryId = 1;
        SubCategory subcategory = createSampleSubcategory();
        when(subcategoryRepository.findById(subcategoryId)).thenReturn(Optional.of(subcategory));

        Optional<SubCategory> retrievedSubcategory = subcategoryService.getSubcategoryById(subcategoryId);

        assertEquals(subcategoryId, retrievedSubcategory.get().getSubcategoryId());
        assertEquals(subcategory.getSubcategoryName(), retrievedSubcategory.get().getSubcategoryName());
        assertEquals(subcategory.getSubcategoryDescription(), retrievedSubcategory.get().getSubcategoryDescription());
    }

    @Test
    public void testUpdateSubcategory() {
        int subcategoryId = 1;
        SubCategory subcategoryToUpdate = createSampleSubcategory();
        when(subcategoryRepository.save(any(SubCategory.class))).thenReturn(subcategoryToUpdate);

        SubCategory updatedSubcategory = subcategoryService.updateSubcategory(subcategoryId, subcategoryToUpdate);

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

    private SubCategory createSampleSubcategory() {
        SubCategory subcategory = new SubCategory();
        subcategory.setSubcategoryId(1);
        subcategory.setSubcategoryName("Sample Subcategory");
        subcategory.setSubcategoryDescription("Sample Subcategory Description");
        subcategory.setCategoryModel(new Category()); 
        return subcategory;
    }
}
