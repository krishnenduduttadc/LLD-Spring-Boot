package com.example.demo2.vendingmachine.state;

import com.example.demo2.vendingmachine.model.Product;
import com.example.demo2.vendingmachine.model.VendingMachine;

public class DispensingState implements State {

    private Product product;

    public DispensingState(Product product) {
        this.product = product;
    }

    @Override
    public void insertMoney(VendingMachine machine, int amount) {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Dispensing " + product.getName());
        machine.getInventory().deduct(product.getCode());

        int change = machine.getCurrentMoney() - product.getPrice();
        if (change > 0) {
            System.out.println("Returning change: " + change);
        }

        machine.setCurrentMoney(0);

        if (machine.getInventory().getStock().values().stream().allMatch(q -> q == 0)) {
            machine.setState(new SoldOutState());
        } else {
            machine.setState(new IdleState());
        }
    }
}
