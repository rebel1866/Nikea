package com.nikea.productservice.service.logic.exception;

public class ProductServiceException extends Throwable {
    public ProductServiceException(String message) {
        super(message);
    }
    public ProductServiceException(String message, Throwable e) {
        super(message, e);
    }
}
