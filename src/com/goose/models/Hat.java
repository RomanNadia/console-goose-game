package com.goose.models;

public class Hat {
    private String name;
    private int nutrition;
    private int washingLevel;
    private int satisfaction;

    public Hat(String name, int nutrition, int washingLevel, int satisfaction) {
        this.name = name;
        this.nutrition = nutrition;
        this.washingLevel = washingLevel;
        this.satisfaction = satisfaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNutrition() {
        return nutrition;
    }

    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }

    public int getWashingLevel() {
        return washingLevel;
    }

    public void setWashingLevel(int washingLevel) {
        this.washingLevel = washingLevel;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
}
