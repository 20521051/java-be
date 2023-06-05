package com.backend.store.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.store.models.Address;
import com.backend.store.models.CartItem;
import com.backend.store.models.Product;
import com.backend.store.models.User;
import com.backend.store.repositories.ProductRepository;
import com.backend.store.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(String name, String email, String username, String password, String avatar) {
        try {
            List<User> userAll = userRepository.findAll();
            for (User user : userAll) {
                if (user.getUsername() == username)
                    throw new Error("User already exists");
            }
            User user = new User(name, email, username, password, avatar);

            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public User findUserById(String userId) {
        try {
            return userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public User updateInfo(String userId, User userInfo) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found"));
            if (userInfo.getName() != null) {
                user.setName(userInfo.getName());
            }
            if (userInfo.getEmail() != null) {
                user.setEmail(userInfo.getEmail());
            }
            if (userInfo.getAvatar() != null) {
                user.setAvatar(userInfo.getAvatar());
            }
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }

    }

    public List<Product> getWishlist(String userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new Error("User not found"));

            List<Product> wishlist = user.getWishlist();
            if (wishlist == null) {
                throw new Error("Don't have wishlist");
            }

            return wishlist;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public List<CartItem> getCart(String userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new Error("User not found"));

            List<CartItem> cart = user.getCart();
            if (cart == null) {
                throw new Error("Don't have anything in cart");
            }

            return cart;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    // public CartItem addToCart(String productId, String userId) {
    // try {
    // User user = userRepository.findById(userId)
    // .orElseThrow(() -> new Error("User not found"));

    // Product product = ProductRepository.findById(productId)
    // .orElseThrow(() -> new Error("Product not found"));
    // if (product == null) {
    // throw new RuntimeException("Product not found");
    // }

    // List<CartItem> cart = user.getCart();
    // if (cart == null) {
    // throw new Error("Don't have anything in cart");
    // }

    // return cart;
    // } catch (Exception e) {
    // System.out.println("error: " + e.getMessage());
    // return null;
    // }

    // CartItem existCartItem = user.getCart().stream()
    // .filter(item -> item.getProduct().getId().equals(dto.getProductId()))
    // .findFirst().orElse(null);

    // if (existCartItem != null) {
    // existCartItem.setQuantity(existCartItem.getQuantity() + dto.getQuantity());
    // userRepository.save(user);
    // existCartItem.getProduct().setQuantity(existCartItem.getQuantity());
    // return existCartItem;
    // } else {
    // CartItem cartItem = new CartItem();
    // cartItem.setProduct(product);
    // cartItem.setQuantity(dto.getQuantity());
    // user.getCart().add(cartItem);
    // userRepository.save(user);
    // cartItem.getProduct().setQuantity(cartItem.getQuantity());
    // return cartItem;
    // }
    // }

    // public void removeFromCart(String productId, String userId) {
    // User user = userRepository.findById(userId)
    // .orElseThrow(() -> new RuntimeException("User not found"));

    // CartItem cartItem = user.getCart().stream()
    // .filter(item -> item.getProduct().getId().equals(productId))
    // .findFirst().orElse(null);

    // if (cartItem != null) {
    // user.getCart().remove(cartItem);
    // userRepository.save(user);
    // }
    // }

    // private String hashPasswords(String password) {
    // // Implement password hashing logic here
    // return password;
    // }
}
