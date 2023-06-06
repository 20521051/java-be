package com.backend.store.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orderItems")
public class OrderItem {
    @Id
    @MongoId
    @Field(name = "_id", targetType = FieldType.STRING)
    private String id;

    @Field(name = "product", targetType = FieldType.STRING)
    private String productId;

    @Field(name = "price", targetType = FieldType.INT64)
    private double price;

    @Field(name = "quantity", targetType = FieldType.INT32)
    private int quantity;

    @CreatedDate
    @Field(name = "createAt", targetType = FieldType.DATE_TIME)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field(name = "updateAt", targetType = FieldType.DATE_TIME)
    private LocalDateTime updatedAt;

    // Constructors, getters, and setters
    public OrderItem(String productId, double price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
