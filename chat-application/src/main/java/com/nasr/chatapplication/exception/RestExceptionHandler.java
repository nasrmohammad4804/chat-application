package com.nasr.chatapplication.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseTemplate<String> entityNotFoundExceptionHandler(EntityNotFoundException e){
        return new ResponseTemplate<>(e.getMessage(),NOT_FOUND);
    }
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseTemplate<String> tokenExpirationExceptionHandler(TokenExpiredException e){
        return new ResponseTemplate<>(e.getMessage(),UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseTemplate<String> genericExceptionHandler(Exception e) {
        return new ResponseTemplate<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }
}
