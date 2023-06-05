package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.store.models.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
}// add custom methods if needed }
