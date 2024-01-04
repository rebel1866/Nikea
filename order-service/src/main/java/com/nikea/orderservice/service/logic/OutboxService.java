package com.nikea.orderservice.service.logic;

import com.nikea.orderservice.dao.model.Order;
import com.nikea.orderservice.service.dto.OrderDto;

public interface OutboxService {
    Order saveAndSend(OrderDto orderDto);
}
