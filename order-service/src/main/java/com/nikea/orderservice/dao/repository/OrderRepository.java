package com.nikea.orderservice.dao.repository;

import com.nikea.orderservice.dao.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
