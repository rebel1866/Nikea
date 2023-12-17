package com.nikea.productservice.messaging;

import com.nikea.productservice.service.dto.OrderCreationEvent;
import com.nikea.productservice.service.logic.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private @Autowired ProductService productService;


    @KafkaListener(topics = "${topics.notification.topic.order}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreationEvent orderCreationEvent) {
        productService.decrementAndSendResult(orderCreationEvent);
    }
}
