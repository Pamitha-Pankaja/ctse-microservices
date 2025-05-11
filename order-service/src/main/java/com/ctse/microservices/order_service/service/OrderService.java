package com.ctse.microservices.order_service.service;

import com.ctse.microservices.order_service.client.InventoryClient;
import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository repo, InventoryClient inventoryClient) {
        this.repo = repo;
        this.inventoryClient = inventoryClient;
    }

    public String placeOrder(Order order) {
        Boolean inStock = inventoryClient.isInStock(order.getProductId(), order.getQuantity());

        if (Boolean.TRUE.equals(inStock)) {
            order.setStatus("PLACED");
            repo.save(order);

            // Deduct inventory
            inventoryClient.deductStock(order.getProductId(), order.getQuantity());

            return "Order placed successfully";
        } else {
            return "Insufficient stock";
        }
    }
}
