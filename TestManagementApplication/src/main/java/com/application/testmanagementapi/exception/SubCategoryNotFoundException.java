package com.application.testmanagementapi.exception;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(String msg){
        super(msg);
    }
}
