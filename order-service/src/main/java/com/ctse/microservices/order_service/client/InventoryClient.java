package com.ctse.microservices.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory", url = "${inventory.service.url}")
public interface InventoryClient {

    @GetMapping("/api/inventory/check")
    Boolean isInStock(@RequestParam Long productId, @RequestParam Integer quantity);

//    @PutMapping("/api/inventory/deduct")
//    String deductStock(@RequestParam Long productId, @RequestParam Integer quantity);

    @PutMapping("/api/inventory/deduct")
    Boolean deductStock(@RequestParam Long productId, @RequestParam Integer quantity);
}

