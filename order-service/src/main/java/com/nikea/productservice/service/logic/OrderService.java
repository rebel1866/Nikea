package com.nikea.productservice.service.logic;

import com.nikea.productservice.service.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(String id);

    OrderDto createOrder(OrderDto orderDto);

    OrderDto editOrder(String id, OrderDto orderDto);

    void deleteById(String id);
}
