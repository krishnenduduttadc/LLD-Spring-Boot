package com.example.demo5.splitwise.controller;

import com.example.demo5.splitwise.dto.*;
import com.example.demo5.splitwise.model.*;
import com.example.demo5.splitwise.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/splitwise")
public class SplitwiseController {

    private final ExpenseService expenseService;
    private final Map<String, User> userStore = new HashMap<>();

    public SplitwiseController(ExpenseService expenseService) {
        this.expenseService = expenseService;

        // Sample users
        userStore.put("u1", new User("u1", "Alice", "alice@mail.com"));
        userStore.put("u2", new User("u2", "Bob", "bob@mail.com"));
        userStore.put("u3", new User("u3", "Charlie", "charlie@mail.com"));
    }

    @PostMapping("/expense")
    public ExpenseResponse addExpense(@RequestBody AddExpenseRequest request) {
        User paidBy = getUser(request.getPaidByUserId());
        List<Split> splits = request.getSplits().stream()
                .map(s -> new Split(getUser(s.getUserId()), s.getValue()))
                .collect(Collectors.toList());

        Expense expense = expenseService.addExpense(
                request.getDescription(),
                paidBy,
                request.getAmount(),
                request.getSplitType(),
                splits
        );

        List<ExpenseResponse.SplitDetail> splitDetails = expense.getSplits().stream()
                .map(s -> new ExpenseResponse.SplitDetail(s.getUser().getUserId(), s.getAmount()))
                .toList();

        return new ExpenseResponse(
                expense.getExpenseId(),
                expense.getDescription(),
                expense.getPaidBy().getUserId(),
                expense.getTotalAmount(),
                splitDetails
        );
    }

    @GetMapping("/balances/{userId}")
    public Map<String, Double> getBalances(@PathVariable String userId) {
        return expenseService.getUserBalances(userId);
    }

    @GetMapping("/expense/{expenseId}")
    public ExpenseResponse getExpense(@PathVariable String expenseId) {
        Expense expense = expenseService.getExpense(expenseId);

        List<ExpenseResponse.SplitDetail> splitDetails = expense.getSplits().stream()
                .map(s -> new ExpenseResponse.SplitDetail(s.getUser().getUserId(), s.getAmount()))
                .toList();

        return new ExpenseResponse(
                expense.getExpenseId(),
                expense.getDescription(),
                expense.getPaidBy().getUserId(),
                expense.getTotalAmount(),
                splitDetails
        );
    }

    private User getUser(String userId) {
        if (!userStore.containsKey(userId)) {
            throw new RuntimeException("User not found: " + userId);
        }
        return userStore.get(userId);
    }
}
