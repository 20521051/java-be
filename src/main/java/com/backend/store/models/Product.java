package com.backend.store.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import java.util.List;

@Document(collection = "products")
public class Product {
    @Field(name = "name")
    private String name;

    @DBRef
    @Field(name = "category")
    private Category category;

    @Field(name = "price")
    private double price;

    @Field(name = "thumbnail")
    private String thumbnail;

    @Field(name = "images")
    private List<String> images;

    @Field(name = "description")
    private String description;

    @DBRef
    @Field(name = "comments")
    private List<Comment> comments;

    @Field(name = "rating")
    private double rating;

    @Field(name = "quantity")
    private int quantity;

    @CreatedDate
    @Field(name = "createdAt")
    private Date createdAt;

    @LastModifiedDate
    @Field(name = "updatedAt")
    private Date updatedAt;

    public Product() {
    }

    public Product(String name, Category category, double price, List<String> images, String description, int quantity) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
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
