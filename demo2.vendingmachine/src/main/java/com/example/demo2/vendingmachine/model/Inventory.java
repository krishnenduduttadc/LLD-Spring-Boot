package com.example.demo2.vendingmachine.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        products.put(product.getCode(), product);
        stock.put(product.getCode(), stock.getOrDefault(product.getCode(), 0) + quantity);
    }

    public Product getProduct(String code) {
        return products.get(code);
    }

    public boolean hasStock(String code) {
        return stock.getOrDefault(code, 0) > 0;
    }

    public void deduct(String code) {
        stock.put(code, stock.get(code) - 1);
    }

    public Map<String, Integer> getStock() {
        return stock;
    }
}
