package com.nikea.productservice.controller.exceptionhandler;

import com.nikea.productservice.service.logic.exception.ProductServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ProductServiceException.class})
    public ResponseEntity<String> handleOrderServiceException(Exception e) {
        return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
