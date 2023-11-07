package com.nikea.productservice.service.logic;

import com.nikea.productservice.service.dto.FurnitureDto;

public interface ProductService {
    FurnitureDto getProductById(Long id);
}
