package com.nikea.productservice.service.logic.impl;

import com.nikea.productservice.restintegration.ProductClient;
import com.nikea.productservice.service.logic.ProductService;
import com.nikea.productservice.service.dto.FurnitureDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private @Autowired ProductClient productClient;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackMethod")
    public FurnitureDto getProductById(Long id)  {
        return productClient.getProduct(id);
    }

    public FurnitureDto fallbackMethod(Exception e) {
        logger.debug("Fallback method is executed. Exception message: " + e.getMessage());
        return new FurnitureDto();
    }
}
