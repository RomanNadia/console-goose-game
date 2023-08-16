package com.goose.models;

public class Sessions {
    private String sessionName;

    public Sessions() {
    }

    public Sessions(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}
