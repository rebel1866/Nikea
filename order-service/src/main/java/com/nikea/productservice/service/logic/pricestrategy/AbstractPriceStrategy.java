package com.nikea.productservice.service.logic.pricestrategy;

public abstract class AbstractPriceStrategy implements PriceStrategy {
    public Integer random(int input) {
        return (int) (input + Math.random() * 250);
    }
}
