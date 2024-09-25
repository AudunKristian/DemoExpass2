package com.example.demo.model;
import java.time.Instant;

public class Vote {
    private Instant publishedAt;

    public Vote() {}

    public Vote(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
