package com.nikea.productservice.service.logic;

import com.nikea.event.OrderCreationEvent;
import com.nikea.productservice.service.dto.FurnitureDto;
import com.nikea.productservice.service.logic.exception.ProductServiceException;

import java.util.List;

public interface ProductService {
    FurnitureDto getById(Long id) throws ProductServiceException;


    List<FurnitureDto> getAll();

    FurnitureDto createProduct(FurnitureDto furnitureDto);

    FurnitureDto editProduct(Long id, FurnitureDto furnitureDto) throws ProductServiceException;

    void deleteById(Long id);

    void decrementAmount(Long productId) throws ProductServiceException;

    void decrementAndSendResult(OrderCreationEvent orderCreationEvent);
}
