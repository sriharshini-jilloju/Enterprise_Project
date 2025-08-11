package com.stocktrading.acctservice.model;



import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    private String typeOfUser; // e.g., admin, user
    private double balance;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTypeOfUser() { return typeOfUser; }
    public void setTypeOfUser(String typeOfUser) { this.typeOfUser = typeOfUser; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}