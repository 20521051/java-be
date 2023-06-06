package com.backend.store.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    @MongoId
    @Field(name = "_id", targetType = FieldType.STRING)
    private String id;

    @MongoId
    @Field(name = "name", targetType = FieldType.STRING)
    private String name;

    @MongoId
    @Field(name = "category", targetType = FieldType.STRING)
    private String category;

    @Field(name = "price", targetType = FieldType.INT64)
    private double price;

    @Field(name = "thumbnail", targetType = FieldType.STRING)
    private String thumbnail;

    @Field(name = "images", targetType = FieldType.ARRAY)
    private List<String> images;

    @Field(name = "description", targetType = FieldType.STRING)
    private String description;

    @MongoId
    @Field(name = "comments", targetType = FieldType.ARRAY)
    private List<String> comments;

    @Field(name = "rating", targetType = FieldType.INT64)
    private double rating;

    @Field(name = "quantity", targetType = FieldType.INT32)
    private int quantity;

    @CreatedDate
    @Field(name = "createdAt", targetType = FieldType.DATE_TIME)
    private Date createdAt;

    @LastModifiedDate
    @Field(name = "updatedAt", targetType = FieldType.DATE_TIME)
    private Date updatedAt;

    public Product(String name, String category, double price, List<String> images, String description,
            int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.images = images;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
