package com.ctse.microservices.product_service.repository;

import com.ctse.microservices.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
