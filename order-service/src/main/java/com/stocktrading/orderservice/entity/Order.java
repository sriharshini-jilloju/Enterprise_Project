package com.stocktrading.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    
    @JsonProperty("userId")
    private String userId;
    
    @JsonProperty("stockSymbol")
    private String stockSymbol;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("orderType")
    private String orderType; // BUY, SELL
    
    @JsonProperty("price")
    private Double price;
    
    @JsonProperty("status")
    private String status; // PENDING, COMPLETED, CANCELLED
    
    @JsonProperty("orderDate")
    private LocalDateTime orderDate;
    
    @JsonProperty("accountId")
    private String accountId;

    // Constructors
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Order(String userId, String stockSymbol, Integer quantity, String orderType, Double price, String accountId) {
        this();
        this.userId = userId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.orderType = orderType;
        this.price = price;
        this.accountId = accountId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStockSymbol() { return stockSymbol; }
    public void setStockSymbol(String stockSymbol) { this.stockSymbol = stockSymbol; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
}