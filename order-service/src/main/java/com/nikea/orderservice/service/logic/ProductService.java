package com.nikea.orderservice.service.logic;

import com.nikea.orderservice.service.dto.FurnitureDto;

public interface ProductService {
    FurnitureDto getProductById(Long id);
}
