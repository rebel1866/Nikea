package com.nikea.productservice.service;

import com.nikea.productservice.service.dto.FurnitureDto;

import java.util.List;

public interface ProductService {
    FurnitureDto getById(Long id);


    List<FurnitureDto> getAll();

    FurnitureDto createProduct(FurnitureDto furnitureDto);

    FurnitureDto editProduct(Long id, FurnitureDto furnitureDto);

    void deleteById(Long id);
}
