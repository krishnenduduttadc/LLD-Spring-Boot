package com.example.demo11.invoicesystem.service;

import com.example.demo11.invoicesystem.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvoiceService {

    private final Map<String, Invoice> invoiceStore = new HashMap<>();

    public Invoice generateInvoice(Customer customer, List<Item> items, double taxRate) {
        String invoiceId = UUID.randomUUID().toString();
        Invoice invoice = new Invoice(invoiceId, customer, items, taxRate);
        invoiceStore.put(invoiceId, invoice);
        return invoice;
    }

    public Invoice getInvoice(String invoiceId) {
        return invoiceStore.get(invoiceId);
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoiceStore.values());
    }
}
