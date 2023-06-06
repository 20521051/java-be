package com.backend.store.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.backend.store.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
