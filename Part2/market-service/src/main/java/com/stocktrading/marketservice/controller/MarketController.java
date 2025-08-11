package com.stocktrading.marketservice.controller;



import com.stocktrading.marketservice.model.MarketOrder;
import com.stocktrading.marketservice.repo.MarketOrderRepository;
import com.stocktrading.orderservice.model.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketOrderRepository marketOrderRepository;

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmMarketOrder(@Valid @RequestBody Order order) {  // Receives JSON from order-service
        MarketOrder marketOrder = new MarketOrder();
        marketOrder.setOrderId(order.getOrderId());
        marketOrder.setTransactionId(123); // Placeholder
        marketOrder.setBid(100.0); // Placeholder
        marketOrder.setAsk(101.0);
        marketOrder.setPrevious(99.0);
        marketOrder.setLast(100.5);
        marketOrder.setTypeOfExchange("NYSE");
        marketOrder.orderTransactionConfirmation();
        marketOrderRepository.save(marketOrder);
        return ResponseEntity.ok(marketOrder.getConfirmationStatus());
    }
}