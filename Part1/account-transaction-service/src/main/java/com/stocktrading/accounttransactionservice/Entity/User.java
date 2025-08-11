package com.stocktrading.accounttransactionservice.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String typeOfUser;
    private Map<String, Integer> holdings = new HashMap<>(); // stock symbol -> units

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(String username, String password, String typeOfUser) {
        this.username = username;
        this.password = password;
        this.typeOfUser = typeOfUser;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public void setHoldings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }

    // Utility methods for holdings management
    public void addHolding(String stockSymbol, Integer units) {
        this.holdings.put(stockSymbol, this.holdings.getOrDefault(stockSymbol, 0) + units);
    }

    public void removeHolding(String stockSymbol, Integer units) {
        Integer currentHoldings = this.holdings.getOrDefault(stockSymbol, 0);
        if (currentHoldings >= units) {
            if (currentHoldings.equals(units)) {
                this.holdings.remove(stockSymbol);
            } else {
                this.holdings.put(stockSymbol, currentHoldings - units);
            }
        }
    }

    public Integer getHoldingUnits(String stockSymbol) {
        return this.holdings.getOrDefault(stockSymbol, 0);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", typeOfUser='" + typeOfUser + '\'' +
                ", holdings=" + holdings +
                '}';
    }
}