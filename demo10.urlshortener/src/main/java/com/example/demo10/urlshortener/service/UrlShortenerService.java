package com.example.demo10.urlshortener.service;

import com.example.demo10.urlshortener.model.UrlMapping;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {

    private final Map<String, String> shortToLong = new HashMap<>();
    private final Map<String, String> longToShort = new HashMap<>();
    private final String BASE_URL = "http://short.ly/";

    private final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final AtomicLong counter = new AtomicLong(1); // start at 1

    // Encode number to Base62 (e.g., 125 -> bZ)
    private String encodeBase62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int rem = (int) (num % 62);
            sb.append(BASE62.charAt(rem));
            num /= 62;
        }
        return sb.reverse().toString();
    }

    public UrlMapping shortenUrl(String originalUrl) {
        if (longToShort.containsKey(originalUrl)) {
            String shortKey = longToShort.get(originalUrl);
            return new UrlMapping(BASE_URL + shortKey, originalUrl);
        }

        String shortKey = encodeBase62(counter.getAndIncrement());
        shortToLong.put(shortKey, originalUrl);
        longToShort.put(originalUrl, shortKey);
        return new UrlMapping(BASE_URL + shortKey, originalUrl);
    }

    public String getOriginalUrl(String shortKey) {
        return shortToLong.get(shortKey);
    }
}
