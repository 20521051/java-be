package com.backend.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.backend.store.models.*;
import com.backend.store.services.OrderService;

@RestController
@RequestMapping("/{userId}/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public Order createOrder(@RequestBody Order request, @PathVariable String userId) {
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // String userId = authentication.getName();
        return orderService.createOrder(request, userId);
    }

    @GetMapping()
    public List<Order> getOrderHistoryFollowStatus(@RequestParam("status") String status, @PathVariable String userId) {
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // String userId = authentication.getName();
        return orderService.getOrderByStatus(userId, status);
    }

    @GetMapping("/all")
    public List<Order> getOrder(@PathVariable String userId) {
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // String userId = authentication.getName();
        return orderService.getAllOrders(userId);
    }
}
