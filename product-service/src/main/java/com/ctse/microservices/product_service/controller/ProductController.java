package com.ctse.microservices.product_service.controller;

import com.ctse.microservices.product_service.model.Product;
import com.ctse.microservices.product_service.repository.ProductRepository;
import com.ctse.microservices.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }
}
