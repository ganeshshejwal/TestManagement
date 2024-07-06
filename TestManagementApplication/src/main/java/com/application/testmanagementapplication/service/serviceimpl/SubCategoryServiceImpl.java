package com.application.testmanagementapplication.service.serviceimpl;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.testmanagementapplication.exception.DataAlreadyExistsException;
import com.application.testmanagementapplication.exception.DataNotFoundException;
import com.application.testmanagementapplication.exception.ObjectNullException;
import com.application.testmanagementapplication.model.SubCategory;
import com.application.testmanagementapplication.respository.SubCategoryRepository;
import com.application.testmanagementapplication.service.SubCategoryService;
import com.application.testmanagementapplication.validator.ModelValidator;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelValidator modelValidator;

    public SubCategory createSubCategory(SubCategory subCategory) {
        logger.info("Request for creating SubCategory");
        if (!modelValidator.isSubCategoryValid(subCategory)) {
            logger.error("Error occurred while creating SubCategory");
            throw new ObjectNullException("SubCategory is empty or invalid.");
        }

        String subCategoryName = subCategory.getSubCategoryName();
        Optional<SubCategory> createdSubCategory = subCategoryRepository.findBysubCategoryName(subCategoryName);
        if (createdSubCategory.isPresent()) {
            logger.error("SubCategory is already present");
            throw new DataAlreadyExistsException("SubCategory is already present.");
        }
        subCategoryRepository.save(subCategory);
        logger.info("SubCategory created successfully");
        return subCategory;
    }

    public List<SubCategory> getAllSubCategories() {
        logger.info("Request for fetching all SubCategories");
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        if(subCategories.isEmpty()) {
            logger.error("No subcategories found");
            throw new DataNotFoundException("No subcategories found.");
        }
        logger.info("SubCategories fetched successfully");
        return subCategories;
    }

    public Optional<SubCategory> getSubCategoryById(int id) {
        logger.info("Request for fetching SubCategory using SubCategory Id");
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (!subCategory.isPresent()) {
            logger.error("SubCategory is not found");
            throw new DataNotFoundException("SubCategory is not found.");
        }
        logger.info("SubCategory fetched successfully");
        return subCategory;
    }

    public SubCategory updateSubCategory(int id,SubCategory subCategory) {
        logger.info("Request for updating SubCategory using SubCategory Id");
        Optional<SubCategory> createdSubCategory = subCategoryRepository.findById(id);
        if (!createdSubCategory.isPresent()) {
            logger.error("SubCategory is not found");
            throw new DataNotFoundException("SubCategory is not found.");
        }
        subCategory.setSubCategoryId(id);
        subCategoryRepository.save(subCategory);
        logger.info("SubCategory updated successfully");
        return subCategory;
    }

    public void deleteSubCategory(int id) {
        logger.info("Request for deleting SubCategory using SubCategory Id");
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (!subCategory.isPresent()) {
            logger.error("SubCategory is not found");
            throw new DataNotFoundException("SubCategory is not found.");
        }
        subCategoryRepository.deleteById(id);
        logger.info("SubCategory deleted successfully");
    }
}
