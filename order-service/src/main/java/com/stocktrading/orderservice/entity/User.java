package com.stocktrading.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("typeOfUser")
    private String typeOfUser;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("accountBalance")
    private Double accountBalance;

    // Constructors
    public User() {}

    public User(String username, String password, String typeOfUser, String email, Double accountBalance) {
        this.username = username;
        this.password = password;
        this.typeOfUser = typeOfUser;
        this.email = email;
        this.accountBalance = accountBalance;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTypeOfUser() { return typeOfUser; }
    public void setTypeOfUser(String typeOfUser) { this.typeOfUser = typeOfUser; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getAccountBalance() { return accountBalance; }
    public void setAccountBalance(Double accountBalance) { this.accountBalance = accountBalance; }
}