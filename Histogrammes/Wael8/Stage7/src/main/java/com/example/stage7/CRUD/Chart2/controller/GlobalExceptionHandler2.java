package com.example.stage7.CRUD.Chart2.controller;

import com.example.stage7.CRUD.Chart2.exception.Chart2NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler2 {

    @ExceptionHandler(Chart2NotFoundException.class)
    public ResponseEntity<String> handleChart2NotFoundException(Chart2NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
