package com.astro.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LongUrlRequest {
    @JsonProperty("longUrl")
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
