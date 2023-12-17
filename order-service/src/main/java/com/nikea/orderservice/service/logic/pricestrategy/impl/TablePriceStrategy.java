package com.nikea.orderservice.service.logic.pricestrategy.impl;

import com.nikea.orderservice.service.dto.FurnitureType;
import com.nikea.orderservice.service.logic.pricestrategy.AbstractPriceStrategy;
import org.springframework.stereotype.Component;

@Component
public class TablePriceStrategy extends AbstractPriceStrategy {

    private final FurnitureType type = FurnitureType.TABLE;

    @Override
    public FurnitureType getType() {
        return type;
    }


    @Override
    public double calculatePrice(double price) {
        return random((int) (FurnitureType.TABLE.ordinal() + price));
    }
}