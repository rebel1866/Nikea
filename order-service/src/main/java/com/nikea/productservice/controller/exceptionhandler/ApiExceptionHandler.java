package com.nikea.productservice.controller.exceptionhandler;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
