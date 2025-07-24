package com.stocktrading.orderservice.client;

import com.stocktrading.orderservice.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import java.util.List;

@Component
public class MarketServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    public void processOrder(Order order) {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances("market-service");
            if (!instances.isEmpty()) {
                String marketServiceUrl = instances.get(0).getUri().toString();
                restTemplate.postForObject(marketServiceUrl + "/api/market/process", order, String.class);
            }
        } catch (Exception e) {
            System.err.println("Failed to send order to market service: " + e.getMessage());
        }
    }
}