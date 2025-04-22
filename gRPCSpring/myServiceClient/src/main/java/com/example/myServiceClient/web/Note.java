package com.example.myServiceClient.web;

public class Note {
    private String text;
    private String username;
    private String createdAt;

    public Note(String text, String username, String createdAt) {
        this.text = text;
        this.username = username;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
} 