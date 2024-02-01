package com.nikea.orderservice.service.logic.pricestrategy;

public abstract class AbstractPriceStrategy implements PriceStrategy {
    public Double random(int input) {
        return input + Math.random() * 250;
    }
}
