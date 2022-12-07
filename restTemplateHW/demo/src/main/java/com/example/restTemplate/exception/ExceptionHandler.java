package com.example.restTemplate.exception;

public class ExceptionHandler extends RuntimeException{
    public ExceptionHandler(String message) {
        super(message);
    }
}
