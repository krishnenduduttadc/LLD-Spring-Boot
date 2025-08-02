package com.example.demo7.amazoncart.service;

import com.example.demo7.amazoncart.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    private final Map<String, Cart> carts = new HashMap<>();
    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public Cart getCart(String userId) {
        carts.putIfAbsent(userId, new Cart(userId));
        return carts.get(userId);
    }

    public void addToCart(String userId, String productId, int quantity) {
        Product product = productService.getProduct(productId);
        if (product == null || product.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Product unavailable");
        }
        Cart cart = getCart(userId);
        cart.addItem(product, quantity);
    }

    public void updateCart(String userId, String productId, int quantity) {
        Cart cart = getCart(userId);
        cart.updateItem(productId, quantity);
    }

    public void removeFromCart(String userId, String productId) {
        Cart cart = getCart(userId);
        cart.removeItem(productId);
    }

    public void checkout(String userId) {
        Cart cart = getCart(userId);
        for (CartItem item : cart.getItems().values()) {
            Product p = item.getProduct();
            int qty = item.getQuantity();
            p.setAvailableQuantity(p.getAvailableQuantity() - qty);
        }
        cart.clear();
    }

//     public double getDiscountedTotal() {
//     double total = getTotalAmount();
//     if (total > 1000) return total * 0.90; // 10% off
//     return total;
// }

}
