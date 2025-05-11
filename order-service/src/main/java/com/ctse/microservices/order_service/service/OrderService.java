package com.ctse.microservices.order_service.service;

import com.ctse.microservices.order_service.client.InventoryClient;
import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer that handles the business logic related to orders.
 */
@Service
public class OrderService {

    private final OrderRepository repo;
    private final InventoryClient inventoryClient;


    /**
     * Constructor-based dependency injection for repository and inventory client.
     *
     * @param repo the repository to handle database operations for orders
     * @param inventoryClient the client used to communicate with the inventory microservice
     */
    public OrderService(OrderRepository repo, InventoryClient inventoryClient) {
        this.repo = repo;
        this.inventoryClient = inventoryClient;
    }

    /**
     * Places an order if the requested product is in stock.
     *
     * @param order the order details received from the controller
     * @return a string message indicating success or failure
     */
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
