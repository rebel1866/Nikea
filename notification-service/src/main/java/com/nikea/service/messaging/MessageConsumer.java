package com.nikea.service.messaging;

import com.nikea.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private @Autowired NotificationService notificationService;

    @KafkaListener(topics = "${topics.notification.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        notificationService.sendSms(message);
    }
}