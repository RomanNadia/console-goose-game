package com.goose.models;

public class Detergent {
    private String name;
    private int washingLevel;

    public Detergent(String name, int washingLevel) {
        this.name = name;
        this.washingLevel = washingLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWashingLevel() {
        return washingLevel;
    }

    public void setWashingLevel(int washingLevel) {
        this.washingLevel = washingLevel;
    }
}
