package com.example.demo11.invoicesystem.model;

public class Item {
    private String name;
    private double unitPrice;
    private int quantity;

    public Item(String name, double unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }
}
