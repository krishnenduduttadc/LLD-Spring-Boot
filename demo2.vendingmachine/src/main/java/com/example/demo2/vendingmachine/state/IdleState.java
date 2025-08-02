package com.example.demo2.vendingmachine.state;

import com.example.demo2.vendingmachine.model.VendingMachine;

public class IdleState implements State {

    @Override
    public void insertMoney(VendingMachine machine, int amount) {
        machine.setCurrentMoney(amount);
        machine.setState(new HasMoneyState());
        System.out.println("Money inserted. Now select a product.");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Insert money first!");
    }
}
