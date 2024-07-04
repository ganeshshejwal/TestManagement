package com.application.testmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {
    
    @org.springframework.web.bind.annotation.ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> questionNotFoundException(){
        return new ResponseEntity<>("Question is not present in database",HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> subCategoryNotFoundException(SubCategoryNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

}
