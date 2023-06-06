package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.backend.store.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
