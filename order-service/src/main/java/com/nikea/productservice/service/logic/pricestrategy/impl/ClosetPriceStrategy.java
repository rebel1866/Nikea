package com.nikea.productservice.service.logic.pricestrategy.impl;

import com.nikea.productservice.service.dto.FurnitureType;
import com.nikea.productservice.service.logic.pricestrategy.AbstractPriceStrategy;
import org.springframework.stereotype.Component;

@Component
public class ClosetPriceStrategy extends AbstractPriceStrategy {

    private final FurnitureType type = FurnitureType.CLOSET;
    @Override
    public FurnitureType getType() {
        return type;
    }

    @Override
    public double calculatePrice(double price) {
        return random(FurnitureType.CLOSET.ordinal());
    }
}
