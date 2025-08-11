package com.stocktrading.orderservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String username;
    private String stockSymbol;
    private int units;
    private double price;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Order status constants
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";
    public static final String STATUS_FAILED = "FAILED";

    // Default constructor
    public Order() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = STATUS_PENDING;
    }

    // Constructor with parameters
    public Order(String username, String stockSymbol, int units, double price) {
        this();
        this.username = username;
        this.stockSymbol = stockSymbol;
        this.units = units;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getUnits() {
        return units;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Utility methods for order management
    public double getTotalValue() {
        return this.price * this.units;
    }

    public boolean isPending() {
        return STATUS_PENDING.equals(this.status);
    }

    public boolean isCompleted() {
        return STATUS_COMPLETED.equals(this.status);
    }

    public boolean isCancelled() {
        return STATUS_CANCELLED.equals(this.status);
    }

    public boolean isFailed() {
        return STATUS_FAILED.equals(this.status);
    }

    public void markAsCompleted() {
        this.setStatus(STATUS_COMPLETED);
    }

    public void markAsCancelled() {
        this.setStatus(STATUS_CANCELLED);
    }

    public void markAsFailed() {
        this.setStatus(STATUS_FAILED);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", units=" + units +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", totalValue=" + getTotalValue() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id != null ? id.equals(order.id) : order.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}