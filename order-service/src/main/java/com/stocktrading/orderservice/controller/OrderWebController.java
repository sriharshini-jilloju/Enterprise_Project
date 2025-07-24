package com.stocktrading.orderservice.controller;

import com.stocktrading.orderservice.entity.Order;
import com.stocktrading.orderservice.entity.User;
import com.stocktrading.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderWebController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("users", orderService.getAllUsers());
        return "index";
    }

    @GetMapping("/order-form")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("users", orderService.getAllUsers());
        return "order-form";
    }

    @PostMapping("/create-order")
    public String createOrder(@ModelAttribute Order order, Model model) {
        try {
            Order createdOrder = orderService.createOrder(order);
            model.addAttribute("order", createdOrder);
            return "order-success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", orderService.getAllUsers());
            return "order-form";
        }
    }

    @GetMapping("/create-user")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute User user, Model model) {
        User createdUser = orderService.createUser(user);
        return "redirect:/";
    }
}