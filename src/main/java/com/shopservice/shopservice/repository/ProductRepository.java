package com.shopservice.shopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopservice.shopservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
    
}
