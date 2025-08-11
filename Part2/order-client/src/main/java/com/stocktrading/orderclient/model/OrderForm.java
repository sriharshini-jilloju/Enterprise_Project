package com.stocktrading.orderclient.model;



import com.stocktrading.orderservice.model.Order;

public class OrderForm {
    private int quantity;
    private String tickerSymbol;
    private double orderAmt;
    private String addMoreAsRequired;
    private String attribute;
    private Order.OrderType orderType;

    // Getters and Setters
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }
    public double getOrderAmt() { return orderAmt; }
    public void setOrderAmt(double orderAmt) { this.orderAmt = orderAmt; }
    public String getAddMoreAsRequired() { return addMoreAsRequired; }
    public void setAddMoreAsRequired(String addMoreAsRequired) { this.addMoreAsRequired = addMoreAsRequired; }
    public String getAttribute() { return attribute; }
    public void setAttribute(String attribute) { this.attribute = attribute; }
    public Order.OrderType getOrderType() { return orderType; }
    public void setOrderType(Order.OrderType orderType) { this.orderType = orderType; }
}
