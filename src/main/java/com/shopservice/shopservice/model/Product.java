package com.shopservice.shopservice.model;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
@Document(indexName = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    
    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    
}
