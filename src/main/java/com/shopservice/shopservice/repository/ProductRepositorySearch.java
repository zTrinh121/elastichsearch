package com.shopservice.shopservice.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.shopservice.shopservice.model.Product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Repository
public class ProductRepositorySearch{
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "products";

    public Product getProductById(String productId) throws IOException{
        Product product = null;
        GetResponse<Product> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(productId),
                Product.class
        );

        if (response.found()) {
             product = response.source();
            System.out.println("Product name " + product.getName());
        } else {
            System.out.println ("Product not found");
        }

       return product;
    }

    public  List<Product> searchAllProducts() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, Product.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit object : hits){

            System.out.print(((Product) object.source()));
            products.add((Product) object.source());

        }
        return products;
    }

    public List<Product> getProductsByName(String productName) {
        List<Product> products = List.of();
        try {
            SearchResponse<Product> response = elasticsearchClient.search(s -> s
                    .index(indexName)
                    .query(q -> q
                        .match(m -> m
                            .field("name")
                            .query(productName)
                        )
                    ),
                Product.class
            );

            List<Hit<Product>> hits = response.hits().hits();
            
            if (hits.isEmpty()) {
                System.out.println("No products found");
            } else {
                products = hits.stream()
                               .map(Hit::source)
                               .collect(Collectors.toList());

                products.forEach(product -> System.out.println("Product name: " + product.getName()));
            }
        } catch (IOException e) {
            System.err.println("An error occurred while searching for products: " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }    
    
}
