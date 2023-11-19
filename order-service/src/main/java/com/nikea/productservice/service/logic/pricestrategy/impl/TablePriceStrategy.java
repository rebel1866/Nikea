package com.nikea.productservice.service.logic.pricestrategy.impl;

import com.nikea.productservice.service.dto.FurnitureType;
import com.nikea.productservice.service.logic.pricestrategy.AbstractPriceStrategy;

public class TablePriceStrategy extends AbstractPriceStrategy {

    private final FurnitureType type = FurnitureType.TABLE;

    @Override
    public FurnitureType getType() {
        return type;
    }


    @Override
    public int calculatePrice(double price) {
        return random((int) (FurnitureType.TABLE.ordinal() + price));
    }
}
