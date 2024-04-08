package com.example.geminiconnect;

public class ChatBean {
    private int avatars;
    private String message;

    public ChatBean() {

    }

    public ChatBean(int avatars, String message) {
        this.avatars = avatars;
        this.message = message;
    }

    public int getAvatars() {
        return avatars;
    }

    public void setAvatars(int avatars) {
        this.avatars = avatars;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
