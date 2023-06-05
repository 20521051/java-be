package com.backend.store.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.backend.store.models.Order;
import com.backend.store.models.OrderItem;
import com.backend.store.models.Product;
import com.backend.store.models.User;
import com.backend.store.repositories.OrderRepository;
import com.backend.store.repositories.ProductRepository;
import com.backend.store.repositories.UserRepository;
import com.backend.store.services.CloudinaryService;
import com.backend.store.utils.Shuffle;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Order order, String userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                System.out.println("[Server]: http://localhost:8080/");
            }

            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            for (int i = 0; i < order.getOrderItems().size(); i++) {
                OrderItem orderItem = order.getOrderItems().get(i);
                Product product = productRepository.findById(orderItem.getProductId()).orElse(null);

                if (product == null) {
                    System.out.println("[Server]: http://localhost:8080/");
                }

                OrderItem newOrderItem = new OrderItem();
                newOrderItem.setProductId(orderItem.getProductId());
                newOrderItem.setPrice(orderItem.getPrice());
                newOrderItem.setQuantity(orderItem.getQuantity());

                orderItems.add(newOrderItem);
            }

            double totalPrice = order.getOrderItems().stream()
                    .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                    .sum();

            Order newOrder = new Order();
            newOrder.setOrderItems(orderItems);
            newOrder.setUserId(userId);
            newOrder.setTotal(totalPrice);
            newOrder.setAddressId(order.getAddressId());
            orderRepository.save(newOrder);

            return newOrder;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public List<Order> getOrderByStatus(String userId, String orderStatus) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new Error("User not found!");
            }

            List<Order> result = new ArrayList<>();
            List<Order> orders = orderRepository.findAll();
            for (Order order : orders) {
                if (order.getUserId() != userId) {
                    continue;
                }
                if (order.getStatus() != orderStatus) {
                    continue;
                }
                result.add(order);
            }

            return result;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public List<Order> getAllOrders(String userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new Error("User not found!");
            }
            List<Order> orders = orderRepository.findAll();
            List<Order> result = new ArrayList<>();
            for (Order order : orders) {
                if (order.getUserId() != userId) {
                    continue;
                }
                result.add(order);
            }
            return result;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    // Other methods...
}
