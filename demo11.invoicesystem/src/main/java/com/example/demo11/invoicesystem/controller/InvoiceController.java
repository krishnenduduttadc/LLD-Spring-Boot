package com.example.demo11.invoicesystem.controller;

import com.example.demo11.invoicesystem.model.*;
import com.example.demo11.invoicesystem.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/generate")
    public Invoice generateInvoice(@RequestBody InvoiceRequest request) {
        return invoiceService.generateInvoice(
                new Customer(request.getCustomerId(), request.getName(), request.getEmail()),
                request.getItems(),
                request.getTaxRate()
        );
    }

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable String id) {
        return invoiceService.getInvoice(id);
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}
