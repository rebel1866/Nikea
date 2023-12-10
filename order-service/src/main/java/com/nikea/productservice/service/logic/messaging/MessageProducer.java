package com.nikea.productservice.service.logic.messaging;

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

    @Value("${topics.notification.topic}")
    private String topic;

    public void sendMessage(String message) {
        logger.info("MESSAGE SENT FROM PRODUCER END -> " + message);
        kafkaTemplate.send(topic, message);
    }
}