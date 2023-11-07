package com.nikea.productservice.dao.repository;

import com.nikea.productservice.dao.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
