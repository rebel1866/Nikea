package com.nikea.productservice.service.logic.impl;

import com.nikea.productservice.dao.model.Order;
import com.nikea.productservice.dao.repository.OrderRepository;
import com.nikea.productservice.service.exception.OrderServiceException;
import com.nikea.productservice.service.mapper.OrderMapper;
import com.nikea.productservice.service.logic.OrderService;
import com.nikea.productservice.service.logic.ProductService;
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
    private @Autowired ProductService productService;

    @Override
    public List<OrderDto> getAll() {
        return orderMapper.toDto(orderRepository.findAll());
    }

    @Override
    public OrderDto getById(String id) throws OrderServiceException {
        return orderMapper.toDto(orderRepository.findById(id).
                orElseThrow(()-> new OrderServiceException("No order found with given id: " + id)));
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws OrderServiceException {
        FurnitureDto furnitureDto = productService.getProductById(orderDto.getFurnitureId());
        if (furnitureDto.getId() == null) {
           throw new OrderServiceException("No product found with given id: " + orderDto.getFurnitureId());
        }
        Integer totalPrice = calculateTotalPrice(furnitureDto);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setDateTime(LocalDateTime.now());
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto editOrder(String id, OrderDto orderDto) throws OrderServiceException {
        Order order = orderRepository.findById(id).
                orElseThrow(()-> new OrderServiceException("No order found with given id: " + id));
        order.setComment(orderDto.getComment());
        order.setTotalPrice(orderDto.getTotalPrice());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    private Integer calculateTotalPrice(FurnitureDto furnitureDto) {
        // some random logic to calculate Total price based on furniture price, furniture type and available sizes...
        switch (furnitureDto.getType()) {
            case CHAIR -> {
                int avSize = furnitureDto.getAvailableSizes().size();
                return avSize > 1 ? random(avSize) : random((int) Math.round(furnitureDto.getPrice()));
            }
            case TABLE -> {
                return random((int) (FurnitureType.CHAIR.ordinal() + furnitureDto.getPrice()));
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
