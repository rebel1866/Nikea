package com.nikea.productservice.service.logic.impl;

import com.nikea.productservice.dao.model.Order;
import com.nikea.productservice.dao.repository.OrderRepository;
import com.nikea.productservice.service.exception.OrderServiceException;
import com.nikea.productservice.service.logic.pricestrategy.PriceStrategy;
import com.nikea.productservice.service.mapper.OrderMapper;
import com.nikea.productservice.service.logic.OrderService;
import com.nikea.productservice.service.logic.ProductService;
import com.nikea.productservice.service.dto.FurnitureDto;
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
    private @Autowired  List<PriceStrategy> priceStrategies;

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

    private Integer calculateTotalPrice(FurnitureDto furnitureDto) throws OrderServiceException {
        // some random logic to calculate Total price based on furniture price, furniture type and available sizes...
        PriceStrategy strategy =  priceStrategies.stream().filter(el -> el.getType() == furnitureDto.getType()).findFirst().
                orElseThrow(() -> new OrderServiceException("No appropriate strategy found"));
        return strategy.calculatePrice(furnitureDto.getPrice());
    }

}
