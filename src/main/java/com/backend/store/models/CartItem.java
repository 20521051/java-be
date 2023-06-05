package com.backend.store.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cartItems")
public class CartItem {
    @Id
    private String id;
    private String productId;
    private int quantity;

    public CartItem() {
        // Default constructor
    }

    public CartItem(String productId, int quantity) {
        this.productId = productId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}