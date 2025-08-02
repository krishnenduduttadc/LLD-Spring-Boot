package com.example.demo5.splitwise.dto;

import java.util.List;

public class ExpenseResponse {
    private String expenseId;
    private String description;
    private String paidBy;
    private double totalAmount;
    private List<SplitDetail> splits;

    public static class SplitDetail {
        private String userId;
        private double amount;

        public SplitDetail(String userId, double amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public String getUserId() { return userId; }
        public double getAmount() { return amount; }
    }

    public ExpenseResponse(String expenseId, String description, String paidBy, double totalAmount, List<SplitDetail> splits) {
        this.expenseId = expenseId;
        this.description = description;
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.splits = splits;
    }

    public String getExpenseId() { return expenseId; }
    public String getDescription() { return description; }
    public String getPaidBy() { return paidBy; }
    public double getTotalAmount() { return totalAmount; }
    public List<SplitDetail> getSplits() { return splits; }
}
