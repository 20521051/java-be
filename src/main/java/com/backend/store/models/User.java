package com.backend.store.models;

import org.springframework.data.annotation.Id;
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
@Document(collection = "users")
public class User {
    @Id
    @MongoId
    @Field(name = "_id", targetType = FieldType.STRING)
    private String id;

    @Field(name = "name", targetType = FieldType.STRING)
    private String name;

    @Field(name = "email", targetType = FieldType.STRING)
    private String email;

    @Field(name = "username", targetType = FieldType.STRING)
    private String username;

    @Field(name = "password", targetType = FieldType.STRING)
    private String password;

    @Field(name = "avatar", targetType = FieldType.STRING)
    private String avatar;

    @Field(name = "birthday", targetType = FieldType.DATE_TIME)
    private Date birthday;

    @Field(name = "googleId", targetType = FieldType.STRING)
    private String googleId;

    @MongoId
    @Field(name = "addresses", targetType = FieldType.ARRAY)
    private List<String> addresses;

    @MongoId
    @Field(name = "wishlist", targetType = FieldType.ARRAY)
    private List<String> wishlist;

    @MongoId
    @Field(name = "cart", targetType = FieldType.ARRAY)
    private List<String> cart;

    // Constructors, getters, and setters
    public User(String name, String email, String username, String password, String avatar) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }

    public List<String> getCart() {
        return cart;
    }

    public void setCart(List<String> cart) {
        this.cart = cart;
    }
}
