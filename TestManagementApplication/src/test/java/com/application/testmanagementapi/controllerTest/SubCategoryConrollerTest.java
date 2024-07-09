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

import com.application.testmanagementapplication.controller.SubCategoryController;
import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.service.SubCategoryService;

public class SubCategoryConrollerTest {

    @Mock
    Category category;

    @Mock
    SubCategory subCategory;

    @Mock
    SubCategoryService subCategoryService;

    @InjectMocks
    SubCategoryController subCategoryController;

    @BeforeEach
    public void setup() {
        category = new Category(1, "Java", "Collection");
        subCategory = new SubCategory(1,category,"Annotation","Annotation in spring");
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testCreateSubCategory() {
        when(subCategoryService.createSubCategory(subCategory)).thenReturn(subCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.createSubCategory(subCategory);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetAllSubCategories() {
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(subCategory);
        when(subCategoryService.getAllSubCategories()).thenReturn(subCategories);
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getAllSubCategories();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetSubCategoryById() {
        int subCategoryId = 1;
        when(subCategoryService.getSubCategoryById(subCategoryId)).thenReturn(Optional.of(subCategory));
        ResponseEntity<SubCategory> responseEntity = subCategoryController.getSubCategoryById(subCategoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testUpdateSubCategory() {
        int subCategoryId = 1;
        when(subCategoryService.updateSubCategory(subCategoryId, subCategory)).thenReturn(subCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.updateSubCategory(subCategoryId, subCategory);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testDeleteSubCategory() {
        int subCategoryId = 1;
        subCategoryService.deleteSubCategory(subCategoryId);
        ResponseEntity<String> responseEntity = subCategoryController.deleteSubCategory(subCategoryId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    
}
