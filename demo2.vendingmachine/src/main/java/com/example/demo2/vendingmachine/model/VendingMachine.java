package com.example.demo2.vendingmachine.model;

import com.example.demo2.vendingmachine.state.IdleState;
import com.example.demo2.vendingmachine.state.State;

public class VendingMachine {
    private State state;
    private Inventory inventory;
    private int currentMoney;

    public VendingMachine() {
        this.inventory = new Inventory();
        this.state = new IdleState();
        this.currentMoney = 0;
    }

    public void insertMoney(int amount) {
        state.insertMoney(this, amount);
    }

    public void selectProduct(String productCode) {
        state.selectProduct(this, productCode);
    }

    // Getters & Setters
    public Inventory getInventory() { return inventory; }
    public int getCurrentMoney() { return currentMoney; }
    public void setCurrentMoney(int currentMoney) { this.currentMoney = currentMoney; }
    public State getState() { return state; }
    public void setState(State state) { this.state = state; }
}
