package com.stocktrading.orderservice.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private int orderId;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    @NotBlank(message = "Ticker symbol is required")
    private String tickerSymbol;
    private double orderAmt;
    private String addMoreAsRequired;
    private LocalDate orderDate;
    private String attribute;
    @NotNull(message = "Order type is required")
    private OrderType orderType;

    public enum OrderType {
        BUY, SELL
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }
    public double getOrderAmt() { return orderAmt; }
    public void setOrderAmt(double orderAmt) { this.orderAmt = orderAmt; }
    public String getAddMoreAsRequired() { return addMoreAsRequired; }
    public void setAddMoreAsRequired(String addMoreAsRequired) { this.addMoreAsRequired = addMoreAsRequired; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public String getAttribute() { return attribute; }
    public void setAttribute(String attribute) { this.attribute = attribute; }
    public OrderType getOrderType() { return orderType; }
    public void setOrderType(OrderType orderType) { this.orderType = orderType; }

    public void sellOrder() {
        // Logic for selling order (placeholder)
        System.out.println("Selling order: " + this);
    }

    public void buyOrder() {
        // Logic for buying order (placeholder)
        System.out.println("Buying order: " + this);
    }
}