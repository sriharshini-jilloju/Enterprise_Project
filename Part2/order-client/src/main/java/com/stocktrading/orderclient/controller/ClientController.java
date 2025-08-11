package com.stocktrading.orderclient.controller;



import com.stocktrading.orderclient.model.OrderForm;
import com.stocktrading.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient webClient;

    @GetMapping("/")
    public String orderForm(Model model) {
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("orderTypes", Order.OrderType.values());
        return "order-form";
    }

    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute OrderForm orderForm, Model model) {
        Order order = new Order();
        order.setOrderId(1); // Placeholder
        order.setQuantity(orderForm.getQuantity());
        order.setTickerSymbol(orderForm.getTickerSymbol());
        order.setOrderAmt(orderForm.getOrderAmt());
        order.setAddMoreAsRequired(orderForm.getAddMoreAsRequired());
        order.setAttribute(orderForm.getAttribute());
        order.setOrderType(orderForm.getOrderType());

        List<ServiceInstance> orderInstances = discoveryClient.getInstances("order-service");
        if (orderInstances.isEmpty()) {
            model.addAttribute("message", "Order service unavailable");
            return "result";
        }
        String orderUrl = orderInstances.get(0).getUri() + "/orders/place";

        Mono<String> response = webClient.post()
                .uri(orderUrl)
                .bodyValue(order)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                          clientResponse -> Mono.error(new RuntimeException("Error")))
                .bodyToMono(String.class);


        String result = response.block();
        model.addAttribute("message", result);
        return "result";
    }
}
