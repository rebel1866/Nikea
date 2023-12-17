package com.nikea.orderservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreationEvent {
    private String orderId;
    private Long furnitureId;
    private ProductDecrementStatus decrementStatus;

    public OrderCreationEvent(String orderId, Long furnitureId) {
        this.orderId = orderId;
        this.furnitureId = furnitureId;
    }
}

