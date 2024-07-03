package com.application.testmanagementapi.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.application.testmanagementapi.model.SubCategoryModel;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategoryModel,Integer> {
    Optional<SubCategoryModel> findBySubCategoryName(String subcategoryName);
}
