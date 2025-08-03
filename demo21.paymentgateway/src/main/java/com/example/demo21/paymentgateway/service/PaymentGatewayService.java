package com.example.demo21.paymentgateway.service;

import com.example.demo21.paymentgateway.model.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PaymentGatewayService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Merchant> merchants = new ConcurrentHashMap<>();
    private final Map<String, PaymentRequest> payments = new ConcurrentHashMap<>();
    private final Map<String, String> idempotencyMap = new ConcurrentHashMap<>();

    public User registerUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Merchant registerMerchant(String id, String name) {
        Merchant merchant = new Merchant(id, name);
        merchants.put(id, merchant);
        return merchant;
    }

    public PaymentRequest initiatePayment(String userId, String merchantId, double amount, String idempotencyKey) {
        if (idempotencyMap.containsKey(idempotencyKey)) {
            return payments.get(idempotencyMap.get(idempotencyKey));
        }

        if (!users.containsKey(userId) || !merchants.containsKey(merchantId)) {
            throw new RuntimeException("Invalid user or merchant");
        }

        PaymentRequest request = new PaymentRequest(userId, merchantId, amount, idempotencyKey);
        payments.put(request.getPaymentId(), request);
        idempotencyMap.put(idempotencyKey, request.getPaymentId());
        return request;
    }

    public PaymentRequest confirmPayment(String paymentId, boolean success) {
        PaymentRequest request = payments.get(paymentId);
        if (request == null) throw new RuntimeException("Invalid payment ID");

        if (request.getStatus() != PaymentStatus.INITIATED) return request;

        request.setStatus(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
        return request;
    }

    public Optional<PaymentRequest> getPaymentStatus(String paymentId) {
        return Optional.ofNullable(payments.get(paymentId));
    }

    public List<PaymentRequest> getAllPayments() {
        return new ArrayList<>(payments.values());
    }
}
