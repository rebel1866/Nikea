package com.nikea.orderservice.service.logic;

import com.nikea.event.OrderCreationEvent;
import com.nikea.orderservice.service.dto.OrderDto;
import com.nikea.orderservice.service.exception.OrderServiceException;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(String id) throws OrderServiceException;

    OrderDto createOrder(OrderDto orderDto) throws OrderServiceException;

    OrderDto editOrder(String id, OrderDto orderDto) throws OrderServiceException;

    void deleteById(String id);

    void handleProductResponse(OrderCreationEvent orderCreationEvent);
}
