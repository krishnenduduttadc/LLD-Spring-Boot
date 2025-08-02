package com.example.demo5.splitwise.strategy;

import com.example.demo5.splitwise.model.*;

import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public boolean validate(List<Split> splits, double totalAmount) {
        double sum = 0;
        for (Split split : splits) {
            sum += split.getAmount();
        }
        return Math.abs(sum - totalAmount) < 0.01; // Allow minor rounding error
    }

    @Override
    public List<Split> calculateSplits(User paidBy, double totalAmount, List<Split> splits) {
        return splits; // already has exact amounts set
    }
}
