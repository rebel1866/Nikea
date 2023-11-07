package com.nikea.productservice.service.mapper;

import com.nikea.productservice.dao.model.Order;
import com.nikea.productservice.service.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setComment(orderDto.getComment());
        order.setDateTime(orderDto.getDateTime());
        order.setFurnitureId(orderDto.getFurnitureId());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }

    public List<Order> toEntity(List<OrderDto> orderDtoList) {
        return orderDtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public OrderDto toDto(Order order) {
        if (order == null) return null;
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setComment(order.getComment());
        orderDto.setDateTime(order.getDateTime());
        orderDto.setFurnitureId(order.getFurnitureId());
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;
    }

    public List<OrderDto> toDto(List<Order> orderList) {
        return orderList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
