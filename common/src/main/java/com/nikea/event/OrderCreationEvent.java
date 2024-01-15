package com.nikea.event;


public class OrderCreationEvent {
    private String orderId;
    private Long furnitureId;
    private ProductDecrementStatus decrementStatus;

    public OrderCreationEvent(String orderId, Long furnitureId, ProductDecrementStatus decrementStatus) {
        this.orderId = orderId;
        this.furnitureId = furnitureId;
        this.decrementStatus = decrementStatus;
    }

    public OrderCreationEvent(String orderId, Long furnitureId) {
        this.orderId = orderId;
        this.furnitureId = furnitureId;
    }

    public OrderCreationEvent() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public ProductDecrementStatus getDecrementStatus() {
        return decrementStatus;
    }

    public void setDecrementStatus(ProductDecrementStatus decrementStatus) {
        this.decrementStatus = decrementStatus;
    }
}

