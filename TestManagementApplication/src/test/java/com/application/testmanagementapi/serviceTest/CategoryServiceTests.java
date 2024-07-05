package com.application.testmanagementapi.serviceTest;

import com.application.testmanagementapi.model.CategoryModel;
import com.application.testmanagementapi.respository.CategoryRepository;
import com.application.testmanagementapi.service.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        CategoryModel category = new CategoryModel(null, "Category One", "Category One Description");
        CategoryModel savedCategory = new CategoryModel(1, "Category One", "Category One Description");
        when(categoryRepository.save(category)).thenReturn(savedCategory);

        CategoryModel result = categoryService.saveCategory(category);

        assertEquals(savedCategory.getCategoryId(), result.getCategoryId());
        assertEquals(savedCategory.getCategoryName(), result.getCategoryName());
        assertEquals(savedCategory.getCategoryDescription(), result.getCategoryDescription());
    }

    @Test
    public void testGetAllCategories() {
        List<CategoryModel> categories = Arrays.asList(
                new CategoryModel(1, "Category One", "Category One Description"),
                new CategoryModel(2, "Category Two", "Category Two Description")
        );
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryModel> retrievedCategories = categoryService.getAllCategories();

        assertEquals(2, retrievedCategories.size());
    }

    @Test
    public void testGetCategoryById() {
        int categoryId = 1;
        CategoryModel category = new CategoryModel(categoryId, "Category One", "Category One Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<CategoryModel> retrievedCategory = categoryService.getCategoryById(categoryId);

        assertEquals(categoryId, retrievedCategory.get().getCategoryId());
        assertEquals("Category One", retrievedCategory.get().getCategoryName());
        assertEquals("Category One Description", retrievedCategory.get().getCategoryDescription());
    }

    @Test
    public void testUpdateCategory() {
        int categoryId = 1;
        CategoryModel categoryToUpdate = new CategoryModel(categoryId, "Updated Category", "Updated Category Description");

        when(categoryRepository.save(categoryToUpdate)).thenReturn(categoryToUpdate);

        CategoryModel updatedCategory = categoryService.updateCategory(categoryId, categoryToUpdate);

        assertEquals(categoryId, updatedCategory.getCategoryId());
        assertEquals("Updated Category", updatedCategory.getCategoryName());
        assertEquals("Updated Category Description", updatedCategory.getCategoryDescription());
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 1;

        doNothing().when(categoryRepository).deleteById(categoryId);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
