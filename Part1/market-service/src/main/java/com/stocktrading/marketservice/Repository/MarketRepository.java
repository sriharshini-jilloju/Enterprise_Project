package com.stocktrading.marketservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stocktrading.marketservice.Entity.MarketItem;


public interface MarketRepository extends MongoRepository<MarketItem, String> {
    MarketItem findByStockSymbol(String stockSymbol);
}
