package com.application.testmanagementapi.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

}
