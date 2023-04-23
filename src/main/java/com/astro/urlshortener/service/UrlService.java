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
    private static final String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    @Autowired
    private UrlRepository urlRepository;

    private static String toBase64(Long id) {

        // Convert the integer to base 64
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            long remainder = id % 64;
            sb.append(base64Chars.charAt((int) remainder));
            id /= 64;
        }

        // Reverse the base 64 string and return it
        return sb.reverse().toString();
    }

    public String generateShortUrl(final Long id) {
        // encode the long url in base64
        String encodedUrl = toBase64(id);

        int appendTimes = (encodedUrl.length() < 7 ? 7 - encodedUrl.length() : 0);

        String shortUrl = BASE_URL + encodedUrl + "=".repeat(appendTimes);
        return shortUrl;

    }

    public String generateNewUrl(final String longUrl) {
        String shortUrl = "";
        URL newUrl = new URL();
        newUrl.setLongUrl(longUrl);
        newUrl.setShortUrl(longUrl);
        saveUrl(newUrl);
        Optional<URL> currentUrl = findUrlByLongUrl(longUrl);
        if (currentUrl.isPresent()) {
            Long id = currentUrl.get().getId();
            shortUrl = generateShortUrl(id);
            currentUrl.get().setShortUrl(shortUrl);
            saveUrl(currentUrl.get());
        }
        return shortUrl;
    }

    public Optional<URL> findUrlbyShortUrl(final String shortUrl) {
        return urlRepository.findURLByShortUrl(shortUrl);
    }

    public URL saveUrl(final URL url) {
        return urlRepository.save(url);
    }


    public Optional<URL> findUrlByLongUrl(String longUrl) {
        return urlRepository.findURLByLongUrl(longUrl);
    }
}
