package com.shopservice.shopservice.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.shopservice.shopservice.model.Product;
import com.shopservice.shopservice.repository.ProductRepositorySearch;
import com.shopservice.shopservice.service.ProductService;

@RestController
public class ProductController {
    private final ProductService productService;
    private final ProductRepositorySearch searchService;

    public ProductController(ProductService productService, ProductRepositorySearch searchService) {
        this.productService = productService;
        this.searchService = searchService;
    }

    @QueryMapping
    public List<Product> findAll() throws Exception {
        return productService.findAll();
    }

    @QueryMapping
    public Product findOne(@Argument String id) throws Exception {
        return searchService.getProductById(id);
    }

    @QueryMapping
    public Product create(Product product){
        return productService.create(product);
    }

    @QueryMapping
    public Product update(String id, Product product) {
        return productService.update(id, product);
    }

    @QueryMapping
    public void delete(String id) {
        productService.delete(id);
    }

    @QueryMapping
    public List<Product> findByName(@Argument String name) {
        return searchService.getProductsByName(name);
    }
}
