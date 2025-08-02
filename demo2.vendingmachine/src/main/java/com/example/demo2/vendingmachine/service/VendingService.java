package com.example.demo2.vendingmachine.service;

import com.example.demo2.vendingmachine.model.Product;
import com.example.demo2.vendingmachine.model.VendingMachine;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.Map;

@Service
public class VendingService {

    private VendingMachine vendingMachine;

    @PostConstruct
    public void init() {
        vendingMachine = new VendingMachine();

        // Initialize some products
        vendingMachine.getInventory().addProduct(new Product("A1", "Coke", 25), 5);
        vendingMachine.getInventory().addProduct(new Product("B1", "Pepsi", 30), 3);
        vendingMachine.getInventory().addProduct(new Product("C1", "Soda", 20), 4);
    }

    public void insertMoney(int amount) {
        vendingMachine.insertMoney(amount);
    }

    public void selectProduct(String code) {
        vendingMachine.selectProduct(code);
    }

    public Map<String, Integer> getStock() {
        return vendingMachine.getInventory().getStock();
    }
}
