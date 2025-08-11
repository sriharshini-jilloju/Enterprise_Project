package com.stocktrading.orderservice.repo;


import com.stocktrading.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}