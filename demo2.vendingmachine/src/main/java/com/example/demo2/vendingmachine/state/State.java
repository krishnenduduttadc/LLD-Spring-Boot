package com.example.demo2.vendingmachine.state;

import com.example.demo2.vendingmachine.model.Product;
import com.example.demo2.vendingmachine.model.VendingMachine;

public interface State {
    void insertMoney(VendingMachine machine, int amount);
    void selectProduct(VendingMachine machine, String productCode);
}
