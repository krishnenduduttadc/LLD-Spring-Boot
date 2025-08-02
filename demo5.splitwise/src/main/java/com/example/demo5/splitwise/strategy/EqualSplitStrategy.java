package com.example.demo5.splitwise.strategy;

import com.example.demo5.splitwise.model.*;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public boolean validate(List<Split> splits, double totalAmount) {
        return splits != null && !splits.isEmpty();
    }

    @Override
    public List<Split> calculateSplits(User paidBy, double totalAmount, List<Split> splits) {
        int n = splits.size();
        double splitAmount = Math.round((totalAmount / n) * 100.0) / 100.0;

        for (Split split : splits) {
            split.setAmount(splitAmount);
        }

        return splits;
    }
}
