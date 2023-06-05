package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.store.models.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    // Custom query methods (if needed)
}
