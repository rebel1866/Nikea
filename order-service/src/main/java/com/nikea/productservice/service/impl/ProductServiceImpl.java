package com.nikea.productservice.service.impl;

import com.nikea.productservice.restintegration.ProductClient;
import com.nikea.productservice.service.ProductService;
import com.nikea.productservice.service.dto.FurnitureDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private @Autowired ProductClient productClient;

    @CircuitBreaker(name = "breaker", fallbackMethod = "fallbackMethod")
    @Override
    public FurnitureDto getProductById(Long id) {
        return productClient.getProduct(id);
    }

    public FurnitureDto fallbackMethod(Exception e) {
        return new FurnitureDto();
    }
}
