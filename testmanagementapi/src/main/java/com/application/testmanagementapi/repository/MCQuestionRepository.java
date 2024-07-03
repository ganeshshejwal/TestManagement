package com.application.testmanagementapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.testmanagementapi.model.MCQuestion;

@Repository
public interface MCQuestionRepository extends JpaRepository<MCQuestion, Long> {
    
}
