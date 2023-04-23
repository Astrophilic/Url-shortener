package com.astro.urlshortener.model;

import jakarta.persistence.*;

/***
 * The URL model class to contain three fields
 * Long id: id serves as an id of the given long url and will be auto generated.
 * String longUrl: can not be null and is provided by user.
 * String shortUrl: can't be null, and generated using UrlService
 */
@Entity
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String longUrl;

    @Column(nullable = false,unique = true)
    private String shortUrl;


}
