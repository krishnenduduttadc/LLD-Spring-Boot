package com.example.demo13.ratelimiter.service;

import com.example.demo13.ratelimiter.model.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    // userId/IP → TokenBucket
    private final Map<String, TokenBucket> limiters = new ConcurrentHashMap<>();

    private final RateLimitConfig config = new RateLimitConfig(10, 5); // 10 tokens, refill 5/sec

    public boolean isRequestAllowed(String clientId) {
        TokenBucket bucket = limiters.computeIfAbsent(
                clientId,
                id -> new TokenBucket(config.getCapacity(), config.getRefillRatePerSecond())
        );
        return bucket.tryConsume();
    }
}
