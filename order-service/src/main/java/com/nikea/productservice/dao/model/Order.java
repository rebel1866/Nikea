package com.nikea.productservice.dao.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long furnitureId;
    private Integer totalPrice;
    private LocalDateTime dateTime;
    private String comment;
}
