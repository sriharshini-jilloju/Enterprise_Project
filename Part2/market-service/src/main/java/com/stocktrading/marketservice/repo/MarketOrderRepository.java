package com.stocktrading.marketservice.repo;


import com.stocktrading.marketservice.model.MarketOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketOrderRepository extends MongoRepository<MarketOrder, String> {
}