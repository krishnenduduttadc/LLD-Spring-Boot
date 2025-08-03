package com.example.demo13.ratelimiter.controller;

import com.example.demo13.ratelimiter.service.RateLimiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateLimiterController {

    private final RateLimiterService rateLimiterService;

    public RateLimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(@RequestParam String clientId) {
        boolean allowed = rateLimiterService.isRequestAllowed(clientId);
        if (allowed) {
            return ResponseEntity.ok("Request allowed");
        } else {
            return ResponseEntity.status(429).body("Rate limit exceeded");
        }
    }
}
