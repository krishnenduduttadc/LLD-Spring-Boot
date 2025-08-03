package com.example.demo21.paymentgateway.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentRequest {
    private final String paymentId;
    private final String userId;
    private final String merchantId;
    private final double amount;
    private final String idempotencyKey;
    private PaymentStatus status;
    private final LocalDateTime createdAt;

    public PaymentRequest(String userId, String merchantId, double amount, String idempotencyKey) {
        this.paymentId = UUID.randomUUID().toString();
        this.userId = userId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.idempotencyKey = idempotencyKey;
        this.status = PaymentStatus.INITIATED;
        this.createdAt = LocalDateTime.now();
    }

    public String getPaymentId() { return paymentId; }
    public String getUserId() { return userId; }
    public String getMerchantId() { return merchantId; }
    public double getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
