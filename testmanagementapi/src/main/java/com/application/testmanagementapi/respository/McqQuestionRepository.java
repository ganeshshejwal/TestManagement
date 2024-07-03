package com.application.testmanagementapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.testmanagementapi.model.McqQuestionModel;

@Repository
public interface McqQuestionRepository extends JpaRepository<McqQuestionModel, Integer> {
    
}
