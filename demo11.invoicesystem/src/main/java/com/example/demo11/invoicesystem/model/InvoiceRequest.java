package com.example.demo11.invoicesystem.model;

import java.util.List;

public class InvoiceRequest {
    private String customerId;
    private String name;
    private String email;
    private List<Item> items;
    private double taxRate;

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Item> getItems() { return items; }
    public double getTaxRate() { return taxRate; }
}
