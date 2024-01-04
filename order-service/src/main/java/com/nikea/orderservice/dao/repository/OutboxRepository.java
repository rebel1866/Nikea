package com.nikea.orderservice.dao.repository;

import com.nikea.orderservice.dao.model.OutboxMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutboxRepository extends MongoRepository<OutboxMessage, String> {
}
