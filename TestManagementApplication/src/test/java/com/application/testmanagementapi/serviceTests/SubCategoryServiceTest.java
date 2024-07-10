package com.application.testmanagementapi.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.testmanagementapplication.exception.DataAlreadyExistsException;
import com.application.testmanagementapplication.exception.DataNotFoundException;
import com.application.testmanagementapplication.exception.ObjectNullException;
import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.SubCategoryRepository;
import com.application.testmanagementapplication.service.serviceimpl.SubCategoryServiceImpl;
import com.application.testmanagementapplication.validator.ModelValidator;

public class SubCategoryServiceTest {

    @Mock
    Category category;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private ModelValidator modelValidator;

    @InjectMocks
    private SubCategoryServiceImpl subCategoryService;

    private SubCategory subCategory;

    @BeforeEach
    public void setup() {
        category = new Category(1, "Java", "Collection");
        subCategory = new SubCategory(1, category,"Java Basics", "Basic concepts of Java");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSubCategory() {
        when(modelValidator.isSubCategoryValid(subCategory)).thenReturn(true);
        when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

        SubCategory createdSubCategory = subCategoryService.createSubCategory(subCategory);

        assertEquals(subCategory.getSubCategoryId(), createdSubCategory.getSubCategoryId());
        assertEquals(subCategory.getSubCategoryName(), createdSubCategory.getSubCategoryName());
        assertEquals(subCategory.getSubCategoryDescription(), createdSubCategory.getSubCategoryDescription());
    }

    @Test
    public void testGetAllSubCategories() {
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(subCategory);
        when(subCategoryRepository.findAll()).thenReturn(subCategories);

        List<SubCategory> fetchedSubCategories = subCategoryService.getAllSubCategories();

        assertEquals(subCategories.size(), fetchedSubCategories.size());
        assertEquals(subCategories.get(0).getSubCategoryId(), fetchedSubCategories.get(0).getSubCategoryId());
        assertEquals(subCategories.get(0).getSubCategoryName(), fetchedSubCategories.get(0).getSubCategoryName());
        assertEquals(subCategories.get(0).getSubCategoryDescription(), fetchedSubCategories.get(0).getSubCategoryDescription());
    }

    @Test
    public void testGetSubCategoryById() {
        int subCategoryId = 1;
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.of(subCategory));

        Optional<SubCategory> fetchedSubCategory = subCategoryService.getSubCategoryById(subCategoryId);

        assertEquals(subCategoryId, fetchedSubCategory.get().getSubCategoryId());
        assertEquals(subCategory.getSubCategoryName(), fetchedSubCategory.get().getSubCategoryName());
        assertEquals(subCategory.getSubCategoryDescription(), fetchedSubCategory.get().getSubCategoryDescription());
    }

    @Test
    public void testUpdateSubCategory() {
        int subCategoryId = 1;
        SubCategory updatedSubCategory = new SubCategory(subCategoryId, category, "Java Advanced", "Advanced concepts of Java");
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.of(subCategory));
        when(subCategoryRepository.save(updatedSubCategory)).thenReturn(updatedSubCategory);

        SubCategory result = subCategoryService.updateSubCategory(subCategoryId, updatedSubCategory);

        assertEquals(updatedSubCategory.getSubCategoryName(), result.getSubCategoryName());
        assertEquals(updatedSubCategory.getSubCategoryDescription(), result.getSubCategoryDescription());
    }

    @Test
    public void testDeleteSubCategory() {
        int subCategoryId = 1;
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.of(subCategory));

        subCategoryService.deleteSubCategory(subCategoryId);
        
        verify(subCategoryRepository, times(1)).deleteById(subCategoryId);
    }


    // Negative Test Cases

    @Test
    public void testCreateSubCategory_InvalidSubCategory() {
        when(modelValidator.isSubCategoryValid(subCategory)).thenReturn(false);

        try {
            subCategoryService.createSubCategory(subCategory);
            fail("Expected ObjectNullException was not thrown");
        } catch (ObjectNullException ex) {
            assertEquals("SubCategory is empty or invalid.", ex.getMessage());
        }
    }

    @Test
    public void testCreateSubCategory_DuplicateSubCategory() {
        when(modelValidator.isSubCategoryValid(subCategory)).thenReturn(true);
        when(subCategoryRepository.findBysubCategoryName(subCategory.getSubCategoryName())).thenReturn(Optional.of(subCategory));

        try {
            subCategoryService.createSubCategory(subCategory);
            fail("Expected DataAlreadyExistsException was not thrown");
        } catch (DataAlreadyExistsException ex) {
            assertEquals("SubCategory is already present.", ex.getMessage());
        }

        verify(subCategoryRepository, never()).save(any());
    }

    @Test
    public void testGetAllSubCategories_EmptyList() {
        List<SubCategory> subCategories = new ArrayList<>();
        when(subCategoryRepository.findAll()).thenReturn(subCategories);

        try {
            subCategoryService.getAllSubCategories();
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("No subcategories found.", ex.getMessage());
        }
    }

    @Test
    public void testGetSubCategoryById_SubCategoryNotFound() {
        int subCategoryId = 999;
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

        try {
            subCategoryService.getSubCategoryById(subCategoryId);
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("SubCategory is not found.", ex.getMessage());
        }
    }

    @Test
    public void testUpdateSubCategory_SubCategoryNotFound() {
        int subCategoryId = 999;
        SubCategory updatedSubCategory = new SubCategory(subCategoryId, category, "Updated SubCategory", "Updated Description");
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

        try {
            subCategoryService.updateSubCategory(subCategoryId, updatedSubCategory);
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("SubCategory is not found.", ex.getMessage());
        }
    }

    @Test
    public void testDeleteSubCategory_SubCategoryNotFound() {
        int subCategoryId = 999;
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

        try {
            subCategoryService.deleteSubCategory(subCategoryId);
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("SubCategory is not found.", ex.getMessage());
        }
    }

}
