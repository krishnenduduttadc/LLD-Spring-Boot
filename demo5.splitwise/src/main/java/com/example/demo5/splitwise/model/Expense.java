package com.example.demo5.splitwise.model;

import java.util.List;

public class Expense {
    private String expenseId;
    private String description;
    private User paidBy;
    private double totalAmount;
    private SplitType splitType;
    private List<Split> splits;

    public Expense(String expenseId, String description, User paidBy, double totalAmount,
                   SplitType splitType, List<Split> splits) {
        this.expenseId = expenseId;
        this.description = description;
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.splitType = splitType;
        this.splits = splits;
    }

    public String getExpenseId() { return expenseId; }
    public String getDescription() { return description; }
    public User getPaidBy() { return paidBy; }
    public double getTotalAmount() { return totalAmount; }
    public SplitType getSplitType() { return splitType; }
    public List<Split> getSplits() { return splits; }
}
