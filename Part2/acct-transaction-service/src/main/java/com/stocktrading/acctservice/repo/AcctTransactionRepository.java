package com.stocktrading.acctservice.repo;


import com.stocktrading.acctservice.model.AcctTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcctTransactionRepository extends MongoRepository<AcctTransaction, String> {
}