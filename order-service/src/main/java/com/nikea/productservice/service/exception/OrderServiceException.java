package com.nikea.productservice.service.exception;

public class OrderServiceException extends Throwable {
    public OrderServiceException(String message, Throwable e) {
        super(message, e);
    }
    public OrderServiceException(String message) {
        super(message);
    }
}
