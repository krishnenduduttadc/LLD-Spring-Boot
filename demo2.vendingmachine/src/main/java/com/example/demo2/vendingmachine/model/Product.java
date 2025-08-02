package com.example.demo2.vendingmachine.model;

public class Product {
    private String code;
    private String name;
    private int price;

    public Product(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getPrice() { return price; }
}
