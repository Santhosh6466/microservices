package com.microservices.ecomorderservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<String> handleInventoryError(
            MyCustomException exception) {

        return ResponseEntity
                .status(exception.getHttpStatusCode())
                .body("Inventory request failed");
    }
}