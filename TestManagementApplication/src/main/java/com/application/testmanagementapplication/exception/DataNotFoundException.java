package com.application.testmanagementapplication.exception;

public class DataNotFoundException  extends RuntimeException{
    
    public DataNotFoundException(String message){
        super(message);
    }
}
