package com.goose.models;

public class Activity {
    private String name;
    private int satisfaction;

    public Activity(String name, int satisfaction) {
        this.name = name;
        this.satisfaction = satisfaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
}
