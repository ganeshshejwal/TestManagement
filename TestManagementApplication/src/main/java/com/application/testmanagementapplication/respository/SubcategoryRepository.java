package com.application.testmanagementapplication.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.testmanagementapplication.model.SubCategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategory,Integer> {
    Optional<SubCategory> findBysubcategoryName(String subcategoryName);
}
