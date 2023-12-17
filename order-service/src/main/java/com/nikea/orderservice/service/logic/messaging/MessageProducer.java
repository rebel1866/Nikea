package com.nikea.orderservice.service.logic.messaging;

import com.nikea.orderservice.service.dto.OrderCreationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private Logger logger =LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, OrderCreationEvent> orderKafkaTemplate;

    @Value("${topics.notification.topic}")
    private String topic;

    @Value("${topics.notification.topic.order}")
    private String orderTopic;

    public void sendMessage(String message) {
        logger.info("MESSAGE SENT FROM PRODUCER END -> " + message);
        kafkaTemplate.send(topic, message);
    }

    public void sendOrderCreationEvent(OrderCreationEvent orderCreationEvent) {
        orderKafkaTemplate.send(orderTopic, orderCreationEvent);
    }
}