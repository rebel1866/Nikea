package com.nikea.orderservice.service.logic.pricestrategy;

import com.nikea.orderservice.service.dto.FurnitureType;

public interface PriceStrategy {
    FurnitureType getType();
    double calculatePrice(double price);
}
