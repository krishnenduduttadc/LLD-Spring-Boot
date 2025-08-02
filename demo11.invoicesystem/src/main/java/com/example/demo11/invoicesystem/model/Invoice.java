package com.example.demo11.invoicesystem.model;

import java.util.List;

public class Invoice {
    private String invoiceId;
    private Customer customer;
    private List<Item> items;
    private double taxRate;  // e.g., 0.18 for 18%

    public Invoice(String invoiceId, Customer customer, List<Item> items, double taxRate) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.items = items;
        this.taxRate = taxRate;
    }

    public String getInvoiceId() { return invoiceId; }
    public Customer getCustomer() { return customer; }
    public List<Item> getItems() { return items; }

    public double getSubtotal() {
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }

    public double getTaxAmount() {
        return getSubtotal() * taxRate;
    }

    public double getTotalAmount() {
        return getSubtotal() + getTaxAmount();
    }

    public double getTaxRate() {
        return taxRate;
    }
}
