package com.example.demo10.urlshortener.model;

public class UrlMapping {
    private final String shortUrl;
    private final String originalUrl;

    public UrlMapping(String shortUrl, String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
