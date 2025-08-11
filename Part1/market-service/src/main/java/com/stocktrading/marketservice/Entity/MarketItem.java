package com.stocktrading.marketservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market")
public class MarketItem {
    @Id
    private String id;
    private String stockSymbol;
    private String name;
    private double price;
    private int availableUnits;

    // Default constructor
    public MarketItem() {}

    // Constructor with parameters
    public MarketItem(String stockSymbol, String name, double price, int availableUnits) {
        this.stockSymbol = stockSymbol;
        this.name = name;
        this.price = price;
        this.availableUnits = availableUnits;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    // Utility methods for stock management
    public boolean hasAvailableUnits(int requestedUnits) {
        return this.availableUnits >= requestedUnits;
    }

    public void reduceAvailableUnits(int units) {
        if (this.availableUnits >= units) {
            this.availableUnits -= units;
        } else {
            throw new IllegalArgumentException("Not enough units available. Available: " + this.availableUnits + ", Requested: " + units);
        }
    }
}
