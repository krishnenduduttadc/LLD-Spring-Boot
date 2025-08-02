package com.example.demo5.splitwise.dto;

import com.example.demo5.splitwise.model.SplitType;

import java.util.List;

public class AddExpenseRequest {
    private String description;
    private String paidByUserId;
    private double amount;
    private SplitType splitType;
    private List<SplitInput> splits;

    public static class SplitInput {
        private String userId;
        private double value; // For EQUAL: ignored, EXACT: amount, PERCENT: %

        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }

        public double getValue() { return value; }
        public void setValue(double value) { this.value = value; }
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPaidByUserId() { return paidByUserId; }
    public void setPaidByUserId(String paidByUserId) { this.paidByUserId = paidByUserId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public SplitType getSplitType() { return splitType; }
    public void setSplitType(SplitType splitType) { this.splitType = splitType; }

    public List<SplitInput> getSplits() { return splits; }
    public void setSplits(List<SplitInput> splits) { this.splits = splits; }
}

