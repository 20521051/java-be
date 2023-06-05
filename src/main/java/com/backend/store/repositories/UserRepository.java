package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.store.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    // Add custom repository methods if needed
}
