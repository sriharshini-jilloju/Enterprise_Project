package com.stocktrading.orderservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stocktrading.orderservice.Entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {}
