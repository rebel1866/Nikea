package com.nikea.productservice.service.logic.pricestrategy;

import com.google.common.collect.ImmutableList;
import com.nikea.productservice.service.logic.pricestrategy.impl.ChairPriceStrategy;
import com.nikea.productservice.service.logic.pricestrategy.impl.ClosetPriceStrategy;
import com.nikea.productservice.service.logic.pricestrategy.impl.TablePriceStrategy;
import org.springframework.stereotype.Component;

@Component
public class PriceStrategyHolder {
    public ImmutableList<PriceStrategy> getAllTypes() {
        return ImmutableList.of(
                new TablePriceStrategy(),
                new ClosetPriceStrategy(),
                new ChairPriceStrategy());
    }
}
