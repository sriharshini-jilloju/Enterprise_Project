package com.stocktrading.orderservice.controller;



import com.stocktrading.orderservice.model.Order;
import com.stocktrading.orderservice.repo.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient webClient;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody Order order) {
        order.setOrderDate(LocalDate.now());
        orderRepository.save(order);

        // Call market-service to confirm
        List<ServiceInstance> marketInstances = discoveryClient.getInstances("market-service");
        if (marketInstances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Market service unavailable");
        }
        String marketUrl = marketInstances.get(0).getUri() + "/market/confirm";

        Mono<String> marketResponse = webClient.post()
                .uri(marketUrl)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(String.class);

        String marketStatus = marketResponse.block(); // Synchronous for simplicity

        if ("CONFIRMED".equals(marketStatus) && order.getOrderType() == Order.OrderType.BUY) {
            // Call acct-transaction-service for buy confirmation
            List<ServiceInstance> acctInstances = discoveryClient.getInstances("acct-transaction-service");
            if (acctInstances.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Acct service unavailable");
            }
            String acctUrl = acctInstances.get(0).getUri() + "/transactions/confirm-buy";

            Mono<String> acctResponse = webClient.post()
                    .uri(acctUrl)
                    .bodyValue(order)
                    .retrieve()
                    .bodyToMono(String.class);

            String acctStatus = acctResponse.block();
            return ResponseEntity.ok("Order placed, market: " + marketStatus + ", acct: " + acctStatus);
        }

        return ResponseEntity.ok("Order placed, market: " + marketStatus);
    }
}