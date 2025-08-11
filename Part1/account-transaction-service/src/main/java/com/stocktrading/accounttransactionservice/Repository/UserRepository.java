package com.stocktrading.accounttransactionservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.stocktrading.accounttransactionservice.Entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}

