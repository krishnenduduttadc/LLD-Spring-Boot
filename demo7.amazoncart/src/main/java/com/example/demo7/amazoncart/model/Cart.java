package com.example.demo7.amazoncart.model;

import java.util.*;

public class Cart {
    private String userId;
    private Map<String, CartItem> items = new HashMap<>();

    public Cart(String userId) {
        this.userId = userId;
    }

    public void addItem(Product product, int qty) {
        items.putIfAbsent(product.getProductId(), new CartItem(product, 0));
        CartItem item = items.get(product.getProductId());
        item.setQuantity(item.getQuantity() + qty);
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public void updateItem(String productId, int qty) {
        if (items.containsKey(productId)) {
            items.get(productId).setQuantity(qty);
        }
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return items.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        items.clear();
    }

    public String getUserId() {
        return userId;
    }
}
