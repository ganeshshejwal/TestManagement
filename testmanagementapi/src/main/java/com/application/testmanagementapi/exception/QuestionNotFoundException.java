package com.application.testmanagementapi.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String msg){
        super(msg);
    }
}
