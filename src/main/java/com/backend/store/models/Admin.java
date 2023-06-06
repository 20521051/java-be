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
@Document(collection = "admins")
public class Admin {
    @Id
    @MongoId
    @Field(name = "_id", targetType = FieldType.STRING)
    private String id;

    @Field(name = "name", targetType = FieldType.STRING)
    private String name;

    @Field(name = "username", targetType = FieldType.STRING)
    private String username;

    @Field(name = "password", targetType = FieldType.STRING)
    private String password;

    @CreatedDate
    @Field(name = "createdAt", targetType = FieldType.DATE_TIME)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field(name = "updatedAt", targetType = FieldType.DATE_TIME)
    private LocalDateTime updatedAt;

    // Constructors, getters, and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    // Constructors, getters, setters, and other methods
}
