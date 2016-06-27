package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nidhi Mishra on 18/06/16.
 */
public class UrlShortnerResponse {

    @JsonProperty("original_url")
    private String originalUrl;

    @JsonProperty("short_url")
    private String shortUrl;

    public UrlShortnerResponse(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public UrlShortnerResponse() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
