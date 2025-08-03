package com.example.demo21.paymentgateway.model;

public class Merchant {
    private final String merchantId;
    private final String name;

    public Merchant(String merchantId, String name) {
        this.merchantId = merchantId;
        this.name = name;
    }

    public String getMerchantId() { return merchantId; }
    public String getName() { return name; }
}
