package com.stocktrading.marketservice.model;


import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market_orders")
public class MarketOrder {
    @Id
    private String id;
    private int orderId;
    private int transactionId;
    private double bid;
    private double ask;
    private double previous;
    private double last;
    @NotBlank(message = "Type of exchange is required")
    private String typeOfExchange; // e.g., NYSE, TSE, Montreal
    private String confirmationStatus;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public double getBid() { return bid; }
    public void setBid(double bid) { this.bid = bid; }
    public double getAsk() { return ask; }
    public void setAsk(double ask) { this.ask = ask; }
    public double getPrevious() { return previous; }
    public void setPrevious(double previous) { this.previous = previous; }
    public double getLast() { return last; }
    public void setLast(double last) { this.last = last; }
    public String getTypeOfExchange() { return typeOfExchange; }
    public void setTypeOfExchange(String typeOfExchange) { this.typeOfExchange = typeOfExchange; }
    public String getConfirmationStatus() { return confirmationStatus; }
    public void setConfirmationStatus(String confirmationStatus) { this.confirmationStatus = confirmationStatus; }

    public void orderTransactionConfirmation() {
        // Placeholder logic
        this.confirmationStatus = "CONFIRMED";
        System.out.println("Confirmed market order: " + this);
    }
}