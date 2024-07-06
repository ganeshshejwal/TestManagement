package com.application.testmanagementapplication.service.serviceimpl;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapplication.exception.DataAlreadyExistsException;
import com.application.testmanagementapplication.exception.DataNotFoundException;
import com.application.testmanagementapplication.exception.ObjectNullException;
import com.application.testmanagementapplication.model.Category;
import com.application.testmanagementapplication.respository.CategoryRepository;
import com.application.testmanagementapplication.service.CategoryService;
import com.application.testmanagementapplication.validator.ModelValidator;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelValidator modelValidator;

    public Category createCategory(Category category) {
        logger.info("Request for creating Category");
        if(!modelValidator.isCategoryValid(category)){
            logger.error("Error occurred while creating Category");
            throw new ObjectNullException("Category is empty or invalid.");
        }

        String categoryName = category.getCategoryName();
        System.out.println(categoryName);
        Optional<Category> createdCategory = categoryRepository.findBycategoryName(categoryName);
        if(createdCategory.isPresent()){
            logger.error("Category is already present");
            throw new DataAlreadyExistsException("Category is already present.");
        }
        categoryRepository.save(category);
        logger.info("Category created successfully");
        return category;
    }

    public List<Category> getAllCategories() {
        logger.info("Request for fetching all Categories");
        List<Category> categories =  categoryRepository.findAll();
        if(categories.isEmpty()){
            logger.error("No categories found");
            throw new DataNotFoundException("No categories found.");
        }
        logger.info("Categories fetched successfully");
        return categories;
    }

    public Optional<Category> getCategoryById(int id) {
        logger.info("Request for fetching Category using Category Id");
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            logger.error("Category is not found");
            throw new DataNotFoundException("Category is not found.");
        }
        logger.info("Category fetched successfully");
        return category;
    }
    
    public Category updateCategory(int id, Category category) {
        logger.info("Request for updating Category using Category Id");
        Optional<Category> createdCategory = categoryRepository.findById(id);
        if(!createdCategory.isPresent()){
            logger.error("Category is not found");
            throw new DataNotFoundException("Category is not found.");
        }
        category.setCategoryId(id);
        categoryRepository.save(category);
        logger.info("Category updated sucessfully");
        return category;
    }

    public void deleteCategory(int id) {
        logger.info("Request for deleting Category using Category Id");
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            logger.error("Category is not found");
            throw new DataNotFoundException("Category is not found.");
        }
        categoryRepository.deleteById(id);
        logger.info("Category deleted sucessfully");
    }
}
