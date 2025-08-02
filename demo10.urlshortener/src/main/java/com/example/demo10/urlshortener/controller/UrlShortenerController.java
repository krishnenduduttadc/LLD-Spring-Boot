package com.example.demo10.urlshortener.controller;

import com.example.demo10.urlshortener.model.UrlMapping;
import com.example.demo10.urlshortener.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public UrlMapping shorten(@RequestParam String originalUrl) {
        return urlShortenerService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<?> redirect(@PathVariable String shortKey) {
        String longUrl = urlShortenerService.getOriginalUrl(shortKey);
        if (longUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(302).header("Location", longUrl).build();
    }
}
