package com.application.testmanagementapi.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.respository.CategoryRepository;
import com.application.testmanagementapplication.service.CategoryService;

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
        Category category = new Category(null, "Category One", "Category One Description");
        Category savedCategory = new Category(1, "Category One", "Category One Description");
        when(categoryRepository.save(category)).thenReturn(savedCategory);

        Category result = categoryService.saveCategory(category);

        assertEquals(savedCategory.getCategoryId(), result.getCategoryId());
        assertEquals(savedCategory.getCategoryName(), result.getCategoryName());
        assertEquals(savedCategory.getCategoryDescription(), result.getCategoryDescription());
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = Arrays.asList(
                new Category(1, "Category One", "Category One Description"),
                new Category(2, "Category Two", "Category Two Description")
        );
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> retrievedCategories = categoryService.getAllCategories();

        assertEquals(2, retrievedCategories.size());
    }

    @Test
    public void testGetCategoryById() {
        int categoryId = 1;
        Category category = new Category(categoryId, "Category One", "Category One Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<Category> retrievedCategory = categoryService.getCategoryById(categoryId);

        assertEquals(categoryId, retrievedCategory.get().getCategoryId());
        assertEquals("Category One", retrievedCategory.get().getCategoryName());
        assertEquals("Category One Description", retrievedCategory.get().getCategoryDescription());
    }

    @Test
    public void testUpdateCategory() {
        int categoryId = 1;
        Category categoryToUpdate = new Category(categoryId, "Updated Category", "Updated Category Description");

        when(categoryRepository.save(categoryToUpdate)).thenReturn(categoryToUpdate);

        Category updatedCategory = categoryService.updateCategory(categoryId, categoryToUpdate);

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
