package com.astro.urlshortener.controller;

import com.astro.urlshortener.dto.LongUrlRequest;
import com.astro.urlshortener.model.URL;
import com.astro.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten-url")
    public ResponseEntity<String> shortenUrl(@RequestBody LongUrlRequest longUrlRequest) {
        // check if url is already present in db
        String longUrl = longUrlRequest.getLongUrl();
        Optional<URL> url = urlService.findUrlByLongUrl(longUrl);
        String shortUrl = "";
        if (url.isPresent()) {
            return ResponseEntity.ok(url.get().getShortUrl());
        } else {
            shortUrl = this.urlService.generateNewUrl(longUrl);
        }
        return ResponseEntity.ok(shortUrl);
    }
}

