package com.backend.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.backend.store.models.Product;
import com.backend.store.repositories.ProductRepository;

import java.io.File;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product createProduct(String name, Category category, double price, String description, int quantity , List<File> images) {
        Product product = new Product(name, category, price, description, quantity);
        // Set product properties from CreateProductDTO
        // Save product to MongoDB collection
        mongoTemplate.save(product);

        return product;
    }

    public Product updateProduct(String prodId, Product dto, List<File> images) {
        Product product = getProductById(prodId);
        if (product != null) {
            
            if(dto.getCategory()){
                product.setCategory(dto.getCategory());
            }
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setQuantity(dto.getQuantity());
            // Save updated product to MongoDB collection
            mongoTemplate.save(product);

            return product;
        }
        return null;
    }

    public void deleteProduct(String productId) {
        Query query = new Query(Criteria.where("_id").is(productId));
        mongoTemplate.remove(query, Product.class);
    }

    public List<Product> getProductsPagination(int limit, int page) {
        Query query = new Query()
                .limit(limit)
                .skip(limit * (page - 1));

        return mongoTemplate.find(query, Product.class);
    }

    public Product getProductById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Product.class);
    }

    public boolean checkCategoryIdIsBeingUsed(String categoryId) {
        Query query = new Query(Criteria.where("categoryId").is(categoryId));
        return mongoTemplate.exists(query, Product.class);
    }

    public List<Product> getNewestProduct() {
        Query query = new Query().limit(10).with(Sort.by(Sort.Direction.DESC, "createdAt"));
        return mongoTemplate.find(query, Product.class);
    }

    public int countProductsInCategory(String categoryId) {
        Query query = new Query(Criteria.where("categoryId").is(categoryId));
        return (int) mongoTemplate.count(query, Product.class);
    }

    public double findMaxPrice(String categoryId) {
        Criteria criteria = categoryId != null ? Criteria.where("categoryId").is(categoryId) : new Criteria();
        Query query = new Query(criteria).with(Sort.by(Sort.Direction.DESC, "price")).limit(1);
        Product product = mongoTemplate.findOne(query, Product.class);
        return product != null ? product.getPrice() : 0.0;
    }

    public List<Product> filterProduct(int limit, int page, String categoryId, String searchText, double priceFrom, double priceTo, String sort) {
        Query query = new Query();

        if (categoryId != null) {
            query.addCriteria(Criteria.where("categoryId").is(categoryId));
        }
        if (searchText != null) {
            query.addCriteria(Criteria.where("name").regex(searchText, "i"));
        }
        if (priceFrom > 0) {
            query.addCriteria(Criteria.where("price").gte(priceFrom));
        }
        if (priceTo > 0) {
            query.addCriteria(Criteria.where("price").lte(priceTo));
        }

        if (sort != null) {
            Sort.Direction direction = Sort.Direction.ASC;
            if (sort.equals("desc")) {
                direction = Sort.Direction.DESC;
            }
            query.with(Sort.by(direction, "name"));
        }

        query.limit(limit).skip(limit * (page - 1));

        return mongoTemplate.find(query, Product.class);
    }

    public Product getProductForCart(String productId) {
        Query query = new Query(Criteria.where("_id").is(productId));
        return mongoTemplate.findOne(query, Product.class);
    }
}
