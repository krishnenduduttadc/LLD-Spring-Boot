package com.example.demo5.splitwise.service;

import java.util.*;

public class BalanceSheet {
    // Key: userId1 → userId2 → amount userId1 owes to userId2
    private final Map<String, Map<String, Double>> balances = new HashMap<>();

    public void addDebt(String fromUserId, String toUserId, double amount) {
        balances.putIfAbsent(fromUserId, new HashMap<>());
        balances.putIfAbsent(toUserId, new HashMap<>());

        double netAmount = balances.get(fromUserId).getOrDefault(toUserId, 0.0) - 
                           balances.get(toUserId).getOrDefault(fromUserId, 0.0);

        netAmount += amount;

        if (netAmount >= 0) {
            balances.get(fromUserId).put(toUserId, netAmount);
            balances.get(toUserId).put(fromUserId, 0.0);
        } else {
            balances.get(toUserId).put(fromUserId, -netAmount);
            balances.get(fromUserId).put(toUserId, 0.0);
        }
    }

    public Map<String, Double> getUserBalances(String userId) {
        return balances.getOrDefault(userId, new HashMap<>());
    }
}
