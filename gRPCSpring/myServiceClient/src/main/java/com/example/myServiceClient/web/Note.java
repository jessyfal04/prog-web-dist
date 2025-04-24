package com.example.myServiceClient.web;

public class Note {
    private String id;
    private String text;
    private String username;
    private String createdAt;
    private int thumbsUpCount;

    public Note(String id, String text, String username, String createdAt, int thumbsUpCount) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.createdAt = createdAt;
        this.thumbsUpCount = thumbsUpCount;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getThumbsUpCount() {
        return thumbsUpCount;
    }

    public void setThumbsUpCount(int thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
    }
} 