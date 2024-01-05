package com.nikea.productservice.messaging;

import com.nikea.productservice.service.dto.OrderCreationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, OrderCreationEvent> orderKafkaTemplate;

    @Value("${topics.product.topic}")
    private String productTopic;


    public void sendOrderCreationEvent(OrderCreationEvent orderCreationEvent) {
        orderKafkaTemplate.send(productTopic, orderCreationEvent);
    }

}
