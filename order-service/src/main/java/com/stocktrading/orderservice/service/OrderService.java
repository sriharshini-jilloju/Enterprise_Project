package com.stocktrading.orderservice.service;

import com.stocktrading.orderservice.entity.Order;
import com.stocktrading.orderservice.entity.User;
import com.stocktrading.orderservice.repository.OrderRepository;
import com.stocktrading.orderservice.repository.UserRepository;
import com.stocktrading.orderservice.client.MarketServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MarketServiceClient marketServiceClient;

    public Order createOrder(Order order) {
        // Validate user exists
        Optional<User> user = userRepository.findById(order.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        // Save order
        Order savedOrder = orderRepository.save(order);
        
        // Send to market service
        marketServiceClient.processOrder(savedOrder);
        
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Order updateOrderStatus(String orderId, String status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found");
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}