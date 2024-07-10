package com.application.testmanagementapi.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.application.testmanagementapplication.exception.DataAlreadyExistsException;
import com.application.testmanagementapplication.exception.DataNotFoundException;
import com.application.testmanagementapplication.exception.ObjectNullException;
import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.respository.CategoryRepository;
import com.application.testmanagementapplication.service.serviceimpl.CategoryServiceImpl;
import com.application.testmanagementapplication.validator.ModelValidator;

public class CategoryServiceImplTest {

    @Mock
    Category category;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelValidator modelValidator;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup() {
        category = new Category(1, "Java", "Collection");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        when(modelValidator.isCategoryValid(category)).thenReturn(true);
        when(categoryRepository.findBycategoryName(category.getCategoryName())).thenReturn(Optional.empty());
        when(categoryRepository.save(category)).thenReturn(category);
        Category createdCategory = categoryService.createCategory(category);
        assertEquals(category.getCategoryId(), createdCategory.getCategoryId());
        assertEquals(category.getCategoryName(), createdCategory.getCategoryName());
        assertEquals(category.getCategoryDescription(), createdCategory.getCategoryDescription());
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> fetchedCategories = categoryService.getAllCategories();
        assertEquals(categories.size(), fetchedCategories.size());
        assertEquals(categories.get(0).getCategoryId(), fetchedCategories.get(0).getCategoryId());
        assertEquals(categories.get(0).getCategoryName(), fetchedCategories.get(0).getCategoryName());
        assertEquals(categories.get(0).getCategoryDescription(), fetchedCategories.get(0).getCategoryDescription());
    }

    @Test
    public void testGetCategoryById() {
        int categoryId = 0;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Optional<Category> fetchedCategory = categoryService.getCategoryById(categoryId);
        assertEquals(categoryId, fetchedCategory.get().getCategoryId());
        assertEquals(category.getCategoryName(), fetchedCategory.get().getCategoryName());
        assertEquals(category.getCategoryDescription(), fetchedCategory.get().getCategoryDescription());
    }


    @Test
    public void testUpdateCategory() {
        int categoryId = 1;
        Category updatedCategory = new Category(categoryId, "Java Updated", "Updated Collection");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);
        Category result = categoryService.updateCategory(categoryId, updatedCategory);
        assertEquals(updatedCategory.getCategoryName(), result.getCategoryName());
        assertEquals(updatedCategory.getCategoryDescription(), result.getCategoryDescription());
    }


    @Test
    public void testDeleteCategory() {
        int categoryId = 1;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        categoryService.deleteCategory(categoryId);
    }


    // Negative Test Cases

    @Test
    public void testCreateCategory_NullCategory() {
        Category category = null;
        when(modelValidator.isCategoryValid(category)).thenReturn(false);

        try {
            categoryService.createCategory(category);
            fail("Expected ObjectNullException was not thrown");
        } catch (ObjectNullException ex) {
            assertEquals("Category is empty or invalid.", ex.getMessage());
        }
    }

    @Test
    public void testCreateCategory_DuplicateCategory() {
        String categoryName = category.getCategoryName();
        when(modelValidator.isCategoryValid(category)).thenReturn(true);
        when(categoryRepository.findBycategoryName(categoryName)).thenReturn(Optional.of(category));

        try {
            categoryService.createCategory(category);
            fail("Expected DataAlreadyExistsException was not thrown");
        } catch (DataAlreadyExistsException ex) {
            assertEquals("Category is already present.", ex.getMessage());
        }
    }

    @Test
    public void testGetAllCategories_NoCategories() {
        when(categoryRepository.findAll()).thenReturn(java.util.Collections.emptyList());

        try {
            categoryService.getAllCategories();
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("No categories found.", ex.getMessage());
        }
    }

    @Test
    public void testGetCategoryById_CategoryNotFound() {
        int nonExistingId = 999;
        when(categoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        try {
            categoryService.getCategoryById(nonExistingId);
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("Category is not found.", ex.getMessage());
        }
    }

    @Test
    public void testUpdateCategory_CategoryNotFound() {
        int nonExistingId = 999;
        Category categoryToUpdate = new Category(nonExistingId, "Updated Category", "Updated Description");
    
        Mockito.when(categoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());
    
        try {
            categoryService.updateCategory(nonExistingId, categoryToUpdate);
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("Category is not found.", ex.getMessage());
        }
    }    

    @Test
    public void testDeleteCategory_CategoryNotFound() {
        int nonExistingId = 999;
        when(categoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        try {
            categoryService.deleteCategory(nonExistingId);
            fail("Expected DataNotFoundException was not thrown");
        } catch (DataNotFoundException ex) {
            assertEquals("Category is not found.", ex.getMessage());
        }
    }

}
