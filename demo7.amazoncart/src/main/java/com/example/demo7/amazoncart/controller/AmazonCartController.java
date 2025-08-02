package com.example.demo7.amazoncart.controller;

import com.example.demo7.amazoncart.model.*;
import com.example.demo7.amazoncart.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AmazonCartController {

    private final ProductService productService;
    private final CartService cartService;

    public AmazonCartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;

        // Sample Products
        productService.addProduct(new Product("p1", "iPhone", 899.99, 10));
        productService.addProduct(new Product("p2", "Book", 399.99, 100));
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/cart/{userId}/add")
    public String addToCart(@PathVariable String userId,
                            @RequestParam String productId,
                            @RequestParam int quantity) {
        cartService.addToCart(userId, productId, quantity);
        return "Added to cart";
    }

    @PostMapping("/cart/{userId}/update")
    public String updateCart(@PathVariable String userId,
                             @RequestParam String productId,
                             @RequestParam int quantity) {
        cartService.updateCart(userId, productId, quantity);
        return "Updated cart";
    }

    @DeleteMapping("/cart/{userId}/remove")
    public String removeFromCart(@PathVariable String userId,
                                 @RequestParam String productId) {
        cartService.removeFromCart(userId, productId);
        return "Removed from cart";
    }

    @GetMapping("/cart/{userId}")
    public Cart viewCart(@PathVariable String userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/cart/{userId}/checkout")
    public String checkout(@PathVariable String userId) {
        cartService.checkout(userId);
        return "Checkout successful";
    }
}
