package com.nikea.productservice.service.logic.impl;

import com.nikea.productservice.dao.model.Furniture;
import com.nikea.productservice.dao.repository.ProductRepository;
import com.nikea.productservice.service.logic.ProductService;
import com.nikea.productservice.service.dto.FurnitureDto;
import com.nikea.productservice.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {

    private @Autowired ProductRepository productRepository;
    private @Autowired ProductMapper productMapper;

    @Override
    public FurnitureDto getById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<FurnitureDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public FurnitureDto createProduct(FurnitureDto furnitureDto) {
        Furniture furniture = productRepository.save(productMapper.toEntity(furnitureDto));
        return productMapper.toDto(furniture);
    }

    @Override
    public FurnitureDto editProduct(Long id, FurnitureDto furnitureDto) {
        furnitureDto.setId(id);
        return productMapper.toDto(productRepository.save(productMapper.toEntity(furnitureDto)));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
