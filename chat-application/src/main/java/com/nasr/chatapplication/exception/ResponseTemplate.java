package com.nasr.chatapplication.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ResponseTemplate<T> {
    private  T data;
    private  HttpStatus status;

    public ResponseTemplate(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }
}
