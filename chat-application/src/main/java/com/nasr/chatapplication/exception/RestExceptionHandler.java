package com.nasr.chatapplication.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseTemplate<String> genericExceptionHandler(Exception e) {
        return new ResponseTemplate<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }
}
