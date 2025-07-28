package com.stocktrading.marketservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stocktrading.marketservice.Entity.MarketItem;
import com.stocktrading.marketservice.Repository.MarketRepository;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    private MarketRepository repo;

    @GetMapping("/list")
    public List<MarketItem> listItems() {
        return repo.findAll();
    }

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestParam String stock,
                                           @RequestParam int units) {
        MarketItem item = repo.findByStockSymbol(stock);
        if (item == null || item.getAvailableUnits() < units) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough stock");
        }
        item.setAvailableUnits(item.getAvailableUnits() - units);
        repo.save(item);
        return ResponseEntity.ok("Order placed for " + units + " units of " + stock);
    }
}