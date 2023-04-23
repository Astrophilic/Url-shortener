package com.astro.urlshortener.repository;

import com.astro.urlshortener.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<URL, Long> {

    Optional<URL>findURLByShortUrl(String shortUrl);

    Optional<URL>findURLById(Long id);

    Optional<URL>findURLByLongUrl(String longUrl);

}
