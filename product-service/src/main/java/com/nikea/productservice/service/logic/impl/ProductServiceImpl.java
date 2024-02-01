package com.nikea.productservice.service.logic.impl;

import com.nikea.event.OrderCreationEvent;
import com.nikea.event.ProductDecrementStatus;
import com.nikea.productservice.dao.model.Furniture;
import com.nikea.productservice.dao.repository.ProductRepository;
import com.nikea.productservice.messaging.MessageProducer;
import com.nikea.productservice.service.dto.FurnitureDto;
import com.nikea.productservice.service.logic.ProductService;
import com.nikea.productservice.service.logic.exception.ProductServiceException;
import com.nikea.productservice.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private LockRegistry lockRegistry;
    private JdbcTemplate jdbcTemplate;

    private @Autowired MessageProducer messageProducer;

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
    public @Nullable FurnitureDto editProduct(Long id, FurnitureDto furnitureDto) throws ProductServiceException {
        var lock = lockRegistry.obtain(String.valueOf(id));
        FurnitureDto saved = null;
        try {
            boolean lockAcquired = lock.tryLock(2, TimeUnit.SECONDS);
            if (lockAcquired) {
                Furniture furniture = productRepository.findById(id).orElseThrow(() ->
                        new ProductServiceException("No product found with given id: " + id));
                furniture.setFurnitureType(furnitureDto.getType());
                furniture.setColor(furnitureDto.getColor());
                furniture.setPrice(furnitureDto.getPrice());
                furniture.setName(furnitureDto.getName());
                furniture.setAmount(furnitureDto.getAmount());
                saved = productMapper.toDto(productRepository.save(furniture));
            }
        } catch (InterruptedException e) {
            throw new ProductServiceException("Error while lock acquisition", e);
        } finally {
            lock.unlock();
        }
        return saved;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void decrementAmount(Long productId) throws ProductServiceException {
        Furniture furniture = productRepository.findById(productId).orElseThrow(() -> new ProductServiceException("Not found"));
        if (furniture.getAmount() == 0) {
            throw new ProductServiceException("No product left");
        }
        furniture.setAmount(furniture.getAmount() - 1);
        editProduct(productId, productMapper.toDto(furniture));
    }

    @Override
    public void decrementAndSendResult(OrderCreationEvent orderCreationEvent) {
        try {
            decrementAmount(orderCreationEvent.getFurnitureId());
            orderCreationEvent.setDecrementStatus(ProductDecrementStatus.SUCCESS);
        } catch (ProductServiceException e) {
            orderCreationEvent.setDecrementStatus(ProductDecrementStatus.FAIL);
        }
        messageProducer.sendOrderCreationEvent(orderCreationEvent);
    }
}
