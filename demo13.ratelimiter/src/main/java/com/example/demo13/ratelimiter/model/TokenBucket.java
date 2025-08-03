package com.example.demo13.ratelimiter.model;

public class TokenBucket {
    private double tokens;
    private final int capacity;
    private final double refillRatePerSecond;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    // Thread-safe token acquisition
    public synchronized boolean tryConsume() {
        refill();
        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double secondsPassed = (now - lastRefillTimestamp) / 1_000_000_000.0;
        double refillTokens = secondsPassed * refillRatePerSecond;
        tokens = Math.min(capacity, tokens + refillTokens);
        lastRefillTimestamp = now;
    }
}
