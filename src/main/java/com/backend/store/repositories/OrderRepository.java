package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.store.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
