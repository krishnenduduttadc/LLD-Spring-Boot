package com.example.demo5.splitwise.service;

import com.example.demo5.splitwise.model.*;
import com.example.demo5.splitwise.strategy.*;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {

    private final BalanceSheet balanceSheet = new BalanceSheet();
    private final Map<String, Expense> expenses = new HashMap<>();

    public Expense addExpense(String description, User paidBy, double totalAmount,
                              SplitType type, List<Split> splits) {

        SplitStrategy strategy = getStrategy(type);

        if (!strategy.validate(splits, totalAmount)) {
            throw new IllegalArgumentException("Invalid splits");
        }

        List<Split> finalSplits = strategy.calculateSplits(paidBy, totalAmount, splits);

        String expenseId = UUID.randomUUID().toString();
        Expense expense = new Expense(expenseId, description, paidBy, totalAmount, type, finalSplits);
        expenses.put(expenseId, expense);

        // Update balances
        for (Split split : finalSplits) {
            if (!split.getUser().getUserId().equals(paidBy.getUserId())) {
                balanceSheet.addDebt(split.getUser().getUserId(), paidBy.getUserId(), split.getAmount());
            }
        }

        return expense;
    }

    public Map<String, Double> getUserBalances(String userId) {
        return balanceSheet.getUserBalances(userId);
    }

    private SplitStrategy getStrategy(SplitType type) {
        return switch (type) {
            case EQUAL -> new EqualSplitStrategy();
            case EXACT -> new ExactSplitStrategy();
            case PERCENT -> new PercentSplitStrategy();
        };
    }

    public Expense getExpense(String expenseId) {
        return expenses.get(expenseId);
    }
}
