package com.nikea.productservice.service.logic.impl;

import com.nikea.productservice.dao.model.Furniture;
import com.nikea.productservice.dao.repository.ProductRepository;
import com.nikea.productservice.service.dto.FurnitureDto;
import com.nikea.productservice.service.logic.ProductService;
import com.nikea.productservice.service.logic.exception.ProductServiceException;
import com.nikea.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private LockRegistry lockRegistry;
    private JdbcTemplate jdbcTemplate;

    @Override
    public FurnitureDto getById(Long id) throws ProductServiceException {
        return productMapper.toDto(productRepository.findById(id).
                orElseThrow(() -> new ProductServiceException("No product found with id: " + id)));
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
    public @Nullable FurnitureDto editProduct(Long id, FurnitureDto furnitureDto) {
        var lock = lockRegistry.obtain(String.valueOf(id));
        boolean lockAcquired;
        try {
            lockAcquired = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        FurnitureDto saved = null;
        if (lockAcquired) {
            try {
                Furniture furniture = productRepository.findById(id).orElseThrow(RuntimeException::new);
                furniture.setFurnitureType(furnitureDto.getType());
                furniture.setColor(furnitureDto.getColor());
                furniture.setPrice(furnitureDto.getPrice());
                furniture.setName(furnitureDto.getName());
                saved = productMapper.toDto(productRepository.save(furniture));
            } finally {
                lock.unlock();
            }
        }
        return saved;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
