package com.stocktrading.orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.stocktrading.orderservice.Entity.Order;
import com.stocktrading.orderservice.Repository.OrderRepository;

@Controller
public class WebController {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/market")
    public String showMarketPage(Model model) {
        String url = "http://market-service/api/market/list";
        ResponseEntity<MarketItem[]> response = restTemplate.getForEntity(url, MarketItem[].class);
        model.addAttribute("marketItems", response.getBody());
        model.addAttribute("username", "testuser");
        return "market";
    }

    @PostMapping("/api/orders/place")
    public String placeOrder(@RequestParam String username,
                             @RequestParam String stock,
                             @RequestParam int units,
                             Model model) {
        try {
            // Reserve stock in Account Transaction Service
            String reserveUrl = "http://account-transaction-service/api/users/reserve?username=" + username +
                                "&stock=" + stock + "&units=" + units;
            restTemplate.postForObject(reserveUrl, null, String.class);

            // Place order in Market Service
            String marketUrl = "http://market-service/api/market/place?stock=" + stock + "&units=" + units;
            restTemplate.postForObject(marketUrl, null, String.class);

            // Save order locally
            Order order = new Order();
            order.setUsername(username);
            order.setStockSymbol(stock);
            order.setUnits(units);
            order.setStatus("Placed");
            repo.save(order);

            // Add order details to model and return confirmation page
            model.addAttribute("units", units);
            model.addAttribute("stock", stock);
            return "order-confirmation";  // maps to order-confirmation.html
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            model.addAttribute("errorMessage", "User not found in Account Service: " + username);
            // Optionally refresh market list
            String url = "http://market-service/api/market/list";
            ResponseEntity<MarketItem[]> response = restTemplate.getForEntity(url, MarketItem[].class);
            model.addAttribute("marketItems", response.getBody());
            model.addAttribute("username", username);
            return "market";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to place order: " + e.getMessage());
            // Optionally refresh market list
            String url = "http://market-service/api/market/list";
            ResponseEntity<MarketItem[]> response = restTemplate.getForEntity(url, MarketItem[].class);
            model.addAttribute("marketItems", response.getBody());
            model.addAttribute("username", username);
            return "market";
        }
    }


    // Inner class for MarketItem
    public static class MarketItem {
        private String stockSymbol;
        private String name;
        private double price;
        private int availableUnits;

        public String getStockSymbol() { return stockSymbol; }
        public void setStockSymbol(String stockSymbol) { this.stockSymbol = stockSymbol; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public int getAvailableUnits() { return availableUnits; }
        public void setAvailableUnits(int availableUnits) { this.availableUnits = availableUnits; }
    }
}
