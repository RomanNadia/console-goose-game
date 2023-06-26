package com.goose.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Goose {
    //name is a primary key!
    private String name;

    private int maxHunger;
    private int currentHunger;

    private int maxHygiene;
    private int currentHygiene;

    private int maxSatisfaction;
    private int currentSatisfaction;

    private int maxHealth;
    private int currentHealth;

    private Hat hat = new Hat("no hat", 0, 0, 0);  //***********

    public Goose() {
    }

    public Goose(String name, int max_hunger, int current_hunger, int max_hygiene, int current_hygiene,
                 int max_satisfaction, int current_satisfaction, int max_health, int current_health) {
        this.name = name;
        this.maxHunger = max_hunger;
        this.currentHunger = current_hunger;
        this.maxHygiene = max_hygiene;
        this.currentHygiene = current_hygiene;
        this.maxSatisfaction = max_satisfaction;
        this.currentSatisfaction = current_satisfaction;
        this.maxHealth = max_health;
        this.currentHealth = current_health;
    }

    @Override
    public String toString() {
        return "\n Goose " + name + "\n hunger:" + currentHunger + "/" + maxHunger +
                "\n hygiene:" + currentHygiene + "/" + maxHygiene +
                "\n satisfaction:" + currentSatisfaction + "/" + maxSatisfaction +
                "\n health:" + currentHealth + "/" + maxHealth;
    }

    public void feedGoose(Food food) {
        int newCurrentHunger = currentHunger + food.getNutrition();
        if (newCurrentHunger > maxHunger)
            newCurrentHunger = maxHunger;
        currentHunger = newCurrentHunger;
        //update?
        System.out.println("Goose was fed with " + food.getName() + ". \n A description: " + toString());
    }


    public void wearHat(Hat hat) {
        takeOffHat();
        this.hat = hat;
        maxHunger = maxHunger + hat.getNutrition();
        maxHygiene = maxHygiene + hat.getWashingLevel();
        maxSatisfaction = maxSatisfaction + hat.getSatisfaction();
        System.out.println("Goose is wearing " + hat.getName() + ". \n Now goose description is: " + toString());
    }


    public void updateCharacteristics(long timeMilli) {
        starve(timeMilli);
        //gigiene satisfaction
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    public int getMaxHygiene() {
        return maxHygiene;
    }

    public void setMaxHygiene(int maxHygiene) {
        this.maxHygiene = maxHygiene;
    }

    public int getCurrentHygiene() {
        return currentHygiene;
    }

    public void setCurrentHygiene(int currentHygiene) {
        this.currentHygiene = currentHygiene;
    }

    public int getMaxSatisfaction() {
        return maxSatisfaction;
    }

    public void setMaxSatisfaction(int maxSatisfaction) {
        this.maxSatisfaction = maxSatisfaction;
    }

    public int getCurrentSatisfaction() {
        return currentSatisfaction;
    }

    public void setCurrentSatisfaction(int currentSatisfaction) {
        this.currentSatisfaction = currentSatisfaction;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }


    private void starve(long timeMilli) {
        Date date = new Date();
        long timeMilliNow = date.getTime();
        currentHunger = (int)((long)currentHealth - (((timeMilliNow - timeMilli)/10000) * 5));
        if(currentHunger <= 0) {
            currentHunger = 0;
            //shrink health
        }
    }

    private void takeOffHat() {
        maxHunger = maxHunger - hat.getNutrition();
        maxHygiene = maxHygiene - hat.getWashingLevel();
        maxSatisfaction = maxSatisfaction - hat.getSatisfaction();
    }
}
