package com.example.demo7.amazoncart.model;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int availableQuantity;

    public Product(String productId, String name, double price, int availableQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAvailableQuantity() { return availableQuantity; }

    public void setAvailableQuantity(int qty) {
        this.availableQuantity = qty;
    }
}
