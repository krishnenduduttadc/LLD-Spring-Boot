package com.example.demo2.vendingmachine.controller;

import com.example.demo2.vendingmachine.dto.InsertMoneyRequest;
import com.example.demo2.vendingmachine.dto.SelectProductRequest;
import com.example.demo2.vendingmachine.service.VendingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vending")
public class VendingController {

    @Autowired
    private VendingService vendingService;

    @PostMapping("/insert")
    public String insertMoney(@RequestBody InsertMoneyRequest request) {
        vendingService.insertMoney(request.getAmount());
        return "Money inserted: ₹" + request.getAmount();
    }

    @PostMapping("/select")
    public String selectProduct(@RequestBody SelectProductRequest request) {
        vendingService.selectProduct(request.getProductCode());
        return "Selected: " + request.getProductCode();
    }

    @GetMapping("/stock")
    public Map<String, Integer> stock() {
        return vendingService.getStock();
    }
}
