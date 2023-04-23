package com.astro.urlshortener.model;

import jakarta.persistence.*;

/***
 * The URL model class to contain three fields
 * Long id: id serves as an id of the given long url and will be auto generated.
 * String longUrl: can not be null and is provided by user.
 * String shortUrl: can't be null, and generated using UrlService
 */
@Entity
@Table
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String longUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Column(nullable = false,unique = true)
    private String shortUrl;


}
