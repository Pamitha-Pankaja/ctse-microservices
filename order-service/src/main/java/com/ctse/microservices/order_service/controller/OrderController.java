package com.ctse.microservices.order_service.controller;

import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        service.placeOrder(order);
        return ResponseEntity.ok("Order placed successfully.");
    }
}
