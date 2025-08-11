package com.stocktrading.orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;

    // Show registration form (optional, if not in your WebController)
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";  // Thymeleaf template register.html
    }

    // Handle form submission for registration
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String typeOfUser,
                           Model model) {
        try {
            String url = "http://account-transaction-service/api/users/register";
            String body = String.format("{\"username\":\"%s\", \"password\":\"%s\", \"typeOfUser\":\"%s\"}",
                    username, password, typeOfUser);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Registration success, redirect to login page
                return "redirect:/login";
            } else {
                // Registration failed, show error on register page
                model.addAttribute("errorMessage", "Registration failed. Please try again.");
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error during registration: " + e.getMessage());
            return "register";
        }
    }

    // Show login form (optional, if not in your WebController)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Thymeleaf template login.html
    }

    // Handle form submission for login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            String url = "http://account-transaction-service/api/users/login";
            String body = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Login success, redirect to market page
                return "redirect:/market";
            } else {
                model.addAttribute("errorMessage", "Invalid username or password.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error during login: " + e.getMessage());
            return "login";
        }
    }
}
