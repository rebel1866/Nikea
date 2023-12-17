package com.nikea.orderservice.service.logic.messaging;

import com.nikea.orderservice.service.dto.OrderCreationEvent;
import com.nikea.orderservice.service.logic.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private @Autowired OrderService orderService;

    @KafkaListener(topics = "${topics.product.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreationEvent orderCreationEvent) {
       orderService.handleProductResponse(orderCreationEvent);
    }
}
