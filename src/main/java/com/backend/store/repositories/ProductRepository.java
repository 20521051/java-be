package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.store.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
