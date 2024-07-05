package com.application.testmanagementapplication.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String msg){
        super(msg);
    }
}
