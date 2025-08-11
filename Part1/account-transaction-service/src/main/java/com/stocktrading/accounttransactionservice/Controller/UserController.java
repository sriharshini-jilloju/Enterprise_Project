package com.stocktrading.accounttransactionservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stocktrading.accounttransactionservice.Entity.User;

import com.stocktrading.accounttransactionservice.Repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User u = repo.findByUsername(user.getUsername());
        if (u != null && u.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveStock(@RequestParam String username,
                                             @RequestParam String stock,
                                             @RequestParam int units) {
        User u = repo.findByUsername(username);
        if (u == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        u.getHoldings().put(stock, u.getHoldings().getOrDefault(stock, 0) + units);
        repo.save(u);
        return ResponseEntity.ok("Reserved " + units + " units of " + stock);
    }
}