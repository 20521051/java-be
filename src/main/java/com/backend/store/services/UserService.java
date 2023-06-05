package com.backend.store.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.store.models.Address;
import com.backend.store.models.CartItem;
import com.backend.store.models.User;
import com.backend.store.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(String name, String email, String username, String password, String avatar) {
        User user = new User(name, email, username, password, avatar);

        return userRepository.save(user);
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateInfo(String userId, User userInfo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

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
    }

    // public List<WishlistItem> getWishlistProduct(String userId) {
    // User user = userRepository.findById(userId)
    // .orElseThrow(() -> new RuntimeException("User not found"));

    // List<WishlistItem> wishlist = new ArrayList<>();
    // for (Product product : user.getWishlist()) {
    // WishlistItem item = new WishlistItem();
    // item.setId(product.getId());
    // item.setThumbnail(CloudinaryService.getImageUrl(product.getThumbnail()));
    // item.setName(product.getName());
    // item.setPrice(product.getPrice());
    // item.setStock(product.getQuantity() > 0);
    // wishlist.add(item);
    // }

    // return wishlist;
    // }

    public List<CartItem> getCart(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = new ArrayList<>();
        for (CartItem cartItem : user.getCart()) {
            Product product = cartItem.getProduct();
            CartItem item = new CartItem();
            item.setId(product.getId());
            item.setName(product.getName());
            item.setThumbnail(CloudinaryService.getImageUrl(product.getThumbnail()));
            item.setPrice(product.getPrice());
            item.setQuantity(cartItem.getQuantity());
            cartItems.add(item);
        }

        return cartItems;
    }

    public CartItem addToCart(CartItem dto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = ProductService.getProductForCart(dto.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        CartItem existCartItem = user.getCart().stream()
                .filter(item -> item.getProduct().getId().equals(dto.getProductId()))
                .findFirst().orElse(null);

        if (existCartItem != null) {
            existCartItem.setQuantity(existCartItem.getQuantity() + dto.getQuantity());
            userRepository.save(user);
            existCartItem.getProduct().setQuantity(existCartItem.getQuantity());
            return existCartItem;
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(dto.getQuantity());
            user.getCart().add(cartItem);
            userRepository.save(user);
            cartItem.getProduct().setQuantity(cartItem.getQuantity());
            return cartItem;
        }
    }

    public void removeFromCart(String productId, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = user.getCart().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(null);

        if (cartItem != null) {
            user.getCart().remove(cartItem);
            userRepository.save(user);
        }
    }

    private String hashPasswords(String password) {
        // Implement password hashing logic here
        return password;
    }
}
