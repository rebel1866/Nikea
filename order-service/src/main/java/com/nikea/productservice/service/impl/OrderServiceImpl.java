package com.nikea.productservice.service.impl;

import com.nikea.productservice.dao.model.Order;
import com.nikea.productservice.dao.repository.OrderRepository;
import com.nikea.productservice.restintegration.ProductClient;
import com.nikea.productservice.service.OrderMapper;
import com.nikea.productservice.service.OrderService;
import com.nikea.productservice.service.dto.FurnitureDto;
import com.nikea.productservice.service.dto.FurnitureType;
import com.nikea.productservice.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private @Autowired OrderRepository orderRepository;
    private @Autowired OrderMapper orderMapper;
    private @Autowired ProductClient productClient;

    @Override
    public List<OrderDto> getAll() {
        return orderMapper.toDto(orderRepository.findAll());
    }

    @Override
    public OrderDto getById(String id) {
        return orderMapper.toDto(orderRepository.findById(id).orElse(null));
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        FurnitureDto furnitureDto = productClient.getProduct(orderDto.getFurnitureId());
        Integer totalPrice = calculateTotalPrice(furnitureDto);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setDateTime(LocalDateTime.now());
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto editOrder(String id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return new OrderDto();
        }
        order.setComment(orderDto.getComment());
        order.setTotalPrice(orderDto.getTotalPrice());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    private Integer calculateTotalPrice(FurnitureDto furnitureDto) {
        // simulates call to another microservice that calculates total price considering furniture type
        // and available sizes
        // (random logic)
        switch (furnitureDto.getType()) {
            case CHAIR -> {
                int avSize = furnitureDto.getAvailableSizes().size();
                return avSize > 1 ? random(avSize) : random(avSize * 2);
            }
            case TABLE -> {
                return random(FurnitureType.CHAIR.ordinal());
            }
            case CLOSET -> {
                return random(FurnitureType.CLOSET.ordinal());
            }
            default -> {
                return 0;
            }
        }
    }

    private Integer random(int input) {
        return (int) (input + Math.random() * 250);
    }
}
