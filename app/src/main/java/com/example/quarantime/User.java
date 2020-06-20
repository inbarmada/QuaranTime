package com.example.quarantime;

public class User {
    private int userID;
    private String username;
    private int password_hash;

    public User() {}
    public User(int id, String username, int password_hash) {
        this.userID = id;
        this.username = username;
        this.password_hash = password_hash;
    }
    public void setID(int id) {
        this.userID = id;
    }
    public int getID() {
        return this.userID;
    }
    public int getPassHash() {
        return this.password_hash;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }
}