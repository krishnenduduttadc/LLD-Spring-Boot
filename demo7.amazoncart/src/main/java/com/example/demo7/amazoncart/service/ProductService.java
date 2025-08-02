package com.example.demo7.amazoncart.service;

import com.example.demo7.amazoncart.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product p) {
        products.put(p.getProductId(), p);
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
