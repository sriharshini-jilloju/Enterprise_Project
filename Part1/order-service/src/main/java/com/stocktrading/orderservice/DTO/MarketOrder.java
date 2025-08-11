package com.stocktrading.orderservice.DTO;



public class MarketOrder {
    private String stockName;
    private int quantity;

    // Getters and setters
    public String getStockName() {
        return stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

