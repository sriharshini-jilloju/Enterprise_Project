package com.stocktrading.acctservice.controller;



import com.stocktrading.acctservice.model.AcctTransaction;
import com.stocktrading.acctservice.repo.AcctTransactionRepository;
import com.stocktrading.orderservice.model.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private AcctTransactionRepository transactionRepository;

    @PostMapping("/confirm-buy")
    public ResponseEntity<String> confirmBuy(@Valid @RequestBody Order order) {  // Receives JSON from order-service
        AcctTransaction transaction = new AcctTransaction();
        transaction.setOrderId(order.getOrderId());
        transaction.setTransactionId(456); // Placeholder
        transaction.setTransactionType(AcctTransaction.TransactionType.BUY);
        transaction.setTickerSymbol(order.getTickerSymbol());
        transaction.setTransactionPrice(order.getOrderAmt() / order.getQuantity());
        transaction.setOrderDateTime(LocalDateTime.now());
        transaction.setOrderAmt(order.getOrderAmt());
        transaction.setBalanceAmt(10000.0); // Placeholder initial balance
        transaction.confirmBuyTransaction();
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Buy Confirmed, new balance: " + transaction.getBalanceAmt());
    }
}
