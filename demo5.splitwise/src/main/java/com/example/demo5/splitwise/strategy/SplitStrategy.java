package com.example.demo5.splitwise.strategy;

import com.example.demo5.splitwise.model.Expense;
import com.example.demo5.splitwise.model.Split;
import com.example.demo5.splitwise.model.User;

import java.util.List;

public interface SplitStrategy {
    boolean validate(List<Split> splits, double totalAmount);
    List<Split> calculateSplits(User paidBy, double totalAmount, List<Split> splits);
}
