package com.stocktrading.acctservice.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
public class AcctTransaction {
    @Id
    private String id;
    private int transactionId;
    private int orderId;
    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;
    @NotBlank(message = "Ticker symbol is required")
    private String tickerSymbol;
    private double transactionPrice;
    private LocalDateTime orderDateTime;
    private double orderAmt;
    private double balanceAmt;

    public enum TransactionType {
        BUY, SELL
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public TransactionType getTransactionType() { return transactionType; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }
    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }
    public double getTransactionPrice() { return transactionPrice; }
    public void setTransactionPrice(double transactionPrice) { this.transactionPrice = transactionPrice; }
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }
    public double getOrderAmt() { return orderAmt; }
    public void setOrderAmt(double orderAmt) { this.orderAmt = orderAmt; }
    public double getBalanceAmt() { return balanceAmt; }
    public void setBalanceAmt(double balanceAmt) { this.balanceAmt = balanceAmt; }

    public void confirmBuyTransaction() {
        // Placeholder: deduct from balance
        this.balanceAmt -= this.orderAmt;
        System.out.println("Buy transaction confirmed: " + this);
    }

    public void confirmSellTransaction() {
        // Placeholder: add to balance
        this.balanceAmt += this.orderAmt;
        System.out.println("Sell transaction confirmed: " + this);
    }
}