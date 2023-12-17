package com.nikea.orderservice.service.logic.impl;

import com.nikea.orderservice.dao.model.Order;
import com.nikea.orderservice.dao.repository.OrderRepository;
import com.nikea.orderservice.service.dto.FurnitureDto;
import com.nikea.orderservice.service.dto.OrderCreationEvent;
import com.nikea.orderservice.service.dto.OrderDto;
import com.nikea.orderservice.service.dto.ProductDecrementStatus;
import com.nikea.orderservice.service.exception.OrderServiceException;
import com.nikea.orderservice.service.logic.OrderService;
import com.nikea.orderservice.service.logic.ProductService;
import com.nikea.orderservice.service.logic.messaging.MessageProducer;
import com.nikea.orderservice.service.logic.pricestrategy.PriceStrategy;
import com.nikea.orderservice.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ProductService productService;
    private List<PriceStrategy> priceStrategies;
    private MessageProducer messageProducer;

    @Override
    public List<OrderDto> getAll() {
        return orderMapper.toDto(orderRepository.findAll());
    }

    @Override
    public OrderDto getById(String id) throws OrderServiceException {
        return orderMapper.toDto(orderRepository.findById(id).
                orElseThrow(() -> new OrderServiceException("No order found with given id: " + id)));
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws OrderServiceException {
        FurnitureDto furnitureDto = productService.getProductById(orderDto.getFurnitureId());
        if (furnitureDto.getId() == null) {
            throw new OrderServiceException("No product found with given id: " + orderDto.getFurnitureId());
        }
        Double totalPrice = calculateTotalPrice(furnitureDto);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setDateTime(LocalDateTime.now());
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        messageProducer.sendOrderCreationEvent(new OrderCreationEvent(order.getId(), orderDto.getFurnitureId()));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto editOrder(String id, OrderDto orderDto) throws OrderServiceException {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new OrderServiceException("No order found with given id: " + id));
        order.setComment(orderDto.getComment());
        order.setTotalPrice(orderDto.getTotalPrice());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    private Double calculateTotalPrice(FurnitureDto furnitureDto) throws OrderServiceException {
        // some random logic to calculate Total price based on furniture price, furniture type and available sizes...
        PriceStrategy strategy = priceStrategies.stream().filter(el -> el.getType() == furnitureDto.getType()).findFirst().
                orElseThrow(() -> new OrderServiceException("No appropriate strategy found"));
        return strategy.calculatePrice(furnitureDto.getPrice());
    }

    @Override
    public void handleProductResponse(OrderCreationEvent orderCreationEvent) {
        if (orderCreationEvent.getDecrementStatus() == ProductDecrementStatus.FAIL) {
            rollbackOrderCreation(orderCreationEvent.getOrderId());
        } else {
            messageProducer.sendMessage("Your order has been created.");
        }
    }

    private void rollbackOrderCreation(String orderId) {
        deleteById(orderId);
    }
}
