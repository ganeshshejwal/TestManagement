package com.application.testmanagementapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.application.testmanagementapi.model.SubCategoryModel;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategoryModel,Integer> {

}
