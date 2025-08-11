package com.stocktrading.orderservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.stocktrading.orderservice.Entity.Order;
import com.stocktrading.orderservice.Repository.OrderRepository;
import com.stocktrading.orderservice.DTO.MarketOrder;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repo;
    
    @Autowired
    private RestTemplate restTemplate;

    public Order placeOrder(Order order) {
        order.setStatus("PENDING");
        Order savedOrder = repo.save(order);
        
        // Forward to market service
        try {
            forwardToMarket(savedOrder);
            savedOrder.setStatus("COMPLETED");
        } catch (Exception e) {
            savedOrder.setStatus("FAILED");
        }
        
        return repo.save(savedOrder);
    }

    private void forwardToMarket(Order order) {
        // Option 1: Using your MarketOrder DTO
        MarketOrder mo = new MarketOrder();
        mo.setStockName(order.getStockSymbol()); // Assuming you have getStockSymbol()
        mo.setQuantity(order.getUnits()); // Assuming you have getUnits()
        
        // Call market service - make sure the URL matches your MarketController
        restTemplate.postForEntity(
            "http://localhost:8082/api/market/place", 
            mo, 
            String.class
        );
        
       
    }
}