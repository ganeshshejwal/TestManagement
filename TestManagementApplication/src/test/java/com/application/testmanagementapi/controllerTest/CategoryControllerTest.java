package com.application.testmanagementapi.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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

import com.application.testmanagementapplication.controller.CategoryController;
import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.service.CategoryService;

public class CategoryControllerTest {

    @Mock
    Category category;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    public void setup() {
        category = new Category(1, "Java", "Collection");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        when(categoryService.createCategory(category)).thenReturn(category);
        ResponseEntity<Category> responseEntity = categoryController.createCategory(category);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryService.getAllCategories()).thenReturn(categories);
        ResponseEntity<List<Category>> responseEntity = categoryController.getAllCategories();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCategoryById() {
        int categoryId=1;
        when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.of(category));
        ResponseEntity<Category> responseEntity = categoryController.getCategoryById(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateCategory() {
        int categoryId=1;
        when(categoryService.updateCategory(categoryId, category)).thenReturn(category);
        ResponseEntity<Category> responseEntity = categoryController.updateCategory(categoryId, category);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 1;
        categoryService.deleteCategory(categoryId);
        ResponseEntity<String> responseEntity = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}
