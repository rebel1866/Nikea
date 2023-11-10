package com.nikea.productservice.service.logic;

import com.nikea.productservice.service.dto.OrderDto;
import com.nikea.productservice.service.exception.OrderServiceException;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(String id) throws OrderServiceException;

    OrderDto createOrder(OrderDto orderDto) throws OrderServiceException;

    OrderDto editOrder(String id, OrderDto orderDto) throws OrderServiceException;

    void deleteById(String id);
}
