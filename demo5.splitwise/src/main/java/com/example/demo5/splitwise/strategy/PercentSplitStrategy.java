package com.example.demo5.splitwise.strategy;

import com.example.demo5.splitwise.model.*;

import java.util.List;

public class PercentSplitStrategy implements SplitStrategy {

    @Override
    public boolean validate(List<Split> splits, double totalAmount) {
        double totalPercent = 0;
        for (Split split : splits) {
            totalPercent += split.getAmount(); // amount holds % in this strategy
        }
        return Math.abs(totalPercent - 100.0) < 0.01;
    }

    @Override
    public List<Split> calculateSplits(User paidBy, double totalAmount, List<Split> splits) {
        for (Split split : splits) {
            double percent = split.getAmount();
            double calculated = Math.round((percent / 100.0) * totalAmount * 100.0) / 100.0;
            split.setAmount(calculated);
        }
        return splits;
    }
}
