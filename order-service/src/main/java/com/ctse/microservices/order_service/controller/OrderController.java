
package com.ctse.microservices.order_service.controller;

import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handles HTTP requests related to order operations.
 * It acts as a REST controller in the microservice architecture.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    /**
     * Constructor-based dependency injection of OrderService.
     * @param service the order service used to handle business logic.
     */
    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * Endpoint to place a new order.
     * Handles POST requests to /api/orders.
     *
     * @param order the order details sent in the request body
     * @return a ResponseEntity with a success message
     */
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        service.placeOrder(order); // Delegates order processing to the service layer
        return ResponseEntity.ok("Order placed successfully!");
    }
}
