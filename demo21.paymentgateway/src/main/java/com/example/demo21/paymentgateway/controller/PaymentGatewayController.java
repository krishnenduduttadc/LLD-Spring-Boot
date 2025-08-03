package com.example.demo21.paymentgateway.controller;

import com.example.demo21.paymentgateway.model.*;
import com.example.demo21.paymentgateway.service.PaymentGatewayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentGatewayController {

    private final PaymentGatewayService service;

    public PaymentGatewayController(PaymentGatewayService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User registerUser(@RequestParam String id, @RequestParam String name) {
        return service.registerUser(id, name);
    }

    @PostMapping("/merchant")
    public Merchant registerMerchant(@RequestParam String id, @RequestParam String name) {
        return service.registerMerchant(id, name);
    }

    @PostMapping("/initiate")
    public PaymentRequest initiate(@RequestParam String userId,
                                   @RequestParam String merchantId,
                                   @RequestParam double amount,
                                   @RequestParam String idempotencyKey) {
        return service.initiatePayment(userId, merchantId, amount, idempotencyKey);
    }

    @PostMapping("/confirm")
    public PaymentRequest confirm(@RequestParam String paymentId, @RequestParam boolean success) {
        return service.confirmPayment(paymentId, success);
    }

    @GetMapping("/{paymentId}")
    public Optional<PaymentRequest> getStatus(@PathVariable String paymentId) {
        return service.getPaymentStatus(paymentId);
    }

    @GetMapping
    public List<PaymentRequest> all() {
        return service.getAllPayments();
    }
}
