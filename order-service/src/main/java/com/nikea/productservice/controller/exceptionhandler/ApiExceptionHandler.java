package com.nikea.productservice.controller.exceptionhandler;

import com.nikea.productservice.service.exception.OrderServiceException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({CallNotPermittedException.class})
    public ResponseEntity<String> handleCallNotPermittedException() {
        return new ResponseEntity<>("Call not permitted", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<String> handleFeignException() {
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({TimeoutException.class})
    public ResponseEntity<String> handleTimeoutException() {
        return new ResponseEntity<>("Timeout exceeded", HttpStatus.REQUEST_TIMEOUT);
    }
    @ExceptionHandler({OrderServiceException.class})
    public ResponseEntity<String> handleOrderServiceException(Exception e) {
        return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
