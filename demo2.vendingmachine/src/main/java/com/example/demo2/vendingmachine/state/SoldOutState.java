package com.example.demo2.vendingmachine.state;

import com.example.demo2.vendingmachine.model.VendingMachine;

public class SoldOutState implements State {

    @Override
    public void insertMoney(VendingMachine machine, int amount) {
        System.out.println("Machine is sold out. Cannot accept money.");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Machine is sold out.");
    }
}
