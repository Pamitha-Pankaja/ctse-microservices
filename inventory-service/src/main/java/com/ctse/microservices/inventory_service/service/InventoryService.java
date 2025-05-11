package com.ctse.microservices.inventory_service.service;

import com.ctse.microservices.inventory_service.model.Inventory;
import com.ctse.microservices.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    public Optional<Inventory> getInventoryByProductId(Long productId) {
        return repo.findByProductId(productId);
    }

    public Inventory save(Inventory inventory) {
        return repo.save(inventory);
    }

    public boolean deductStock(Long productId, Integer quantity) {
        Optional<Inventory> opt = repo.findByProductId(productId);
        if (opt.isPresent()) {
            Inventory inv = opt.get();
            if (inv.getQuantity() >= quantity) {
                inv.setQuantity(inv.getQuantity() - quantity);
                repo.save(inv);
                return true;
            }
        }
        return false;
    }




}

