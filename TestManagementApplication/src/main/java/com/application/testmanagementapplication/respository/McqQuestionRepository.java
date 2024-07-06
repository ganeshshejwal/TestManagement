package com.application.testmanagementapplication.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.testmanagementapplication.model.McqQuestion;

@Repository
public interface McqQuestionRepository extends JpaRepository<McqQuestion, Integer> {
    Optional<McqQuestion> findByquestion(String question);
}
