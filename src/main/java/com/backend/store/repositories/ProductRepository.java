package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.store.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Add custom repository methods if needed
}
