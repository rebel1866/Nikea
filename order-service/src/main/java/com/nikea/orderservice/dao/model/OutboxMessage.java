package com.nikea.orderservice.dao.model;

import com.nikea.orderservice.service.dto.OrderCreationEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "outbox_messages")
public class OutboxMessage {
    @Id
    private String id;
    private OrderCreationEvent message;
    private LocalDateTime dateTime;
}
