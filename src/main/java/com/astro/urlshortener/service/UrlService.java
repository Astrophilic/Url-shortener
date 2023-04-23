package com.astro.urlshortener.service;

import com.astro.urlshortener.model.URL;
import com.astro.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UrlService {
    private static final String BASE_URL = "https:/tinurl/";
    private static final String BASE64_SET = "0123456789+/abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    private UrlRepository urlRepository;

    public String generateShortUrl(final String longUrl){
        // encode the long url in base64
        String encodedUrl = Base64.getUrlEncoder().encodeToString(longUrl.getBytes());

        String shortUrl = BASE_URL+encodedUrl;
        return shortUrl;


    }

    public Optional<URL> findUrlbyShortUrl(final String shortUrl){
        return urlRepository.findURLByShortUrl(shortUrl);
    }

    public URL saveUrl(final URL url){
        return urlRepository.save(url);
    }

}
