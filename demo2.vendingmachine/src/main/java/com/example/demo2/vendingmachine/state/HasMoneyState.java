package com.example.demo2.vendingmachine.state;

import com.example.demo2.vendingmachine.model.Product;
import com.example.demo2.vendingmachine.model.VendingMachine;

public class HasMoneyState implements State {

    @Override
    public void insertMoney(VendingMachine machine, int amount) {
        machine.setCurrentMoney(machine.getCurrentMoney() + amount);
        System.out.println("Added more money.");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        Product product = machine.getInventory().getProduct(productCode);

        if (product == null) {
            System.out.println("Invalid product code.");
            return;
        }

        if (!machine.getInventory().hasStock(productCode)) {
            System.out.println("Product is out of stock.");
            machine.setState(new IdleState());
            return;
        }

        if (machine.getCurrentMoney() < product.getPrice()) {
            System.out.println("Not enough money.");
            return;
        }

        machine.setState(new DispensingState(product));
        machine.getState().selectProduct(machine, productCode);
    }
}
