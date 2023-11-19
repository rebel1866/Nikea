package com.nikea.productservice.service.logic.pricestrategy;

import com.nikea.productservice.service.dto.FurnitureType;

public interface PriceStrategy {
    FurnitureType getType();
    int calculatePrice(double price);
}
