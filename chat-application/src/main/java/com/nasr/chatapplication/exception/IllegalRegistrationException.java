package com.nasr.chatapplication.exception;

public class IllegalRegistrationException extends RuntimeException{
    public IllegalRegistrationException(String message) {
        super(message);
    }
}
