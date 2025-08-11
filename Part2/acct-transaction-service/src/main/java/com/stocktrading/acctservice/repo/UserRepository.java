package com.stocktrading.acctservice.repo;



import com.stocktrading.acctservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}