package com.shopservice.shopservice.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopservice.shopservice.model.Product;
import com.shopservice.shopservice.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public java.util.List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findOne(String id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        product.setCreated_at(new Date());
        return productRepository.save(product);
    }

    public Product update(String id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setQuantity(product.getQuantity());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setUpdated_at(new Date());
            return productRepository.save(productToUpdate);
        } else {
            return null;
        }
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
