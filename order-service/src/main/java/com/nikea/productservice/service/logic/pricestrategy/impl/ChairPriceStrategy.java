package com.nikea.productservice.service.logic.pricestrategy.impl;

import com.nikea.productservice.service.dto.FurnitureType;
import com.nikea.productservice.service.logic.pricestrategy.AbstractPriceStrategy;
import org.springframework.stereotype.Component;

@Component
public class ChairPriceStrategy extends AbstractPriceStrategy {

    private final FurnitureType type = FurnitureType.CHAIR;
    @Override
    public FurnitureType getType() {
        return type;
    }

    @Override
    public int calculatePrice(double price) {
        return random((int) Math.round(price));
    }
}
