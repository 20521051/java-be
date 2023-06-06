package com.backend.store.repositories;

import com.backend.store.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    // Các phương thức truy vấn tùy chỉnh có thể được thêm ở đây (nếu cần)
}