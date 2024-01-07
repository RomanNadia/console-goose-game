package com.goose.models;

import com.goose.config.AplicationConfig;
import com.goose.info.from.db.HatsInfo;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class Goose {
    private int gooseId;

    private String name;

    private int maxHunger;
    private int currentHunger;

    private int maxHygiene;
    private int currentHygiene;

    private int maxSatisfaction;
    private int currentSatisfaction;

    private int maxHealth;
    private int currentHealth;

    private long lastUpdateTime;

    private Hat currentHat;

    private Sessions gooseSession;

    private int gooseCoins;


    private void setDefaultHat() throws SQLException, ClassNotFoundException {
        HashMap<String, Hat> hats = HatsInfo.getAvailableHats(gooseId);
        currentHat = hats.get(AplicationConfig.ID_OF_DEFAULT_CURRENT_HAT);
    }


    private void setDefaultGooseCoins()  {
        gooseCoins = 100;
    }


    public Goose() throws SQLException, ClassNotFoundException {
    }

    public Goose(String name, int max_hunger, int current_hunger, int max_hygiene, int current_hygiene,
                 int max_satisfaction, int current_satisfaction, int max_health, int current_health,
                 Sessions gooseSession) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.maxHunger = max_hunger;
        this.currentHunger = current_hunger;
        this.maxHygiene = max_hygiene;
        this.currentHygiene = current_hygiene;
        this.maxSatisfaction = max_satisfaction;
        this.currentSatisfaction = current_satisfaction;
        this.maxHealth = max_health;
        this.currentHealth = current_health;
        this.gooseSession = gooseSession;
        setDefaultHat();
        setDefaultGooseCoins();
    }

    public Goose(int id, String name, int maxHunger, int currentHunger, int maxHygiene, int currentHygiene, int maxSatisfaction,
                 int currentSatisfaction, int maxHealth, int currentHealth, long lastUpdateTime, Hat currentHat,
                 int gooseCoins) throws SQLException, ClassNotFoundException {
        gooseId = id;
        this.name = name;
        this.maxHunger = maxHunger;
        this.currentHunger = currentHunger;
        this.maxHygiene = maxHygiene;
        this.currentHygiene = currentHygiene;
        this.maxSatisfaction = maxSatisfaction;
        this.currentSatisfaction = currentSatisfaction;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.lastUpdateTime = lastUpdateTime;
        this.currentHat = currentHat;
        this.gooseCoins = gooseCoins;
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
        System.out.println("Goose was fed with " + food.getName() + ". \n A description: " + toString());
    }


    public void washGoose(Detergent detergent) {
        int newCurrentHygiene = currentHygiene + detergent.getWashingLevel();
        if (newCurrentHygiene > maxHygiene)
            newCurrentHygiene = maxHygiene;
        currentHygiene = newCurrentHygiene;
        System.out.println("Goose was washed with " + detergent.getName() + ". \n A description: " + toString());
    }


    public void interactWithGoose(Activity activity) {
        int newCurrentSatisfaction = currentSatisfaction + activity.getSatisfaction();
        if (newCurrentSatisfaction > maxSatisfaction)
            newCurrentSatisfaction = maxSatisfaction;
        currentSatisfaction = newCurrentSatisfaction;
        System.out.println("Goose and you were busy with " + activity.getName() + ". \n A description: " + toString());
    }


    public void wearHat(Hat hat) {
        takeOffHat();
        this.currentHat = hat;
        maxHunger = maxHunger + hat.getNutrition();
        maxHygiene = maxHygiene + hat.getHygieneBonus();
        maxSatisfaction = maxSatisfaction + hat.getSatisfactionBonus();
        System.out.println("Goose is wearing " + hat.getName() + ". \n Now goose description is: " + toString());
    }


    public void updateCharacteristics() {
        Date date = new Date();
        long timeMilliNow = date.getTime();
        currentHunger = reduceCharacteristic(currentHunger, timeMilliNow);
        currentHygiene = reduceCharacteristic(currentHygiene, timeMilliNow);
        currentSatisfaction = reduceCharacteristic(currentSatisfaction, timeMilliNow);
        lastUpdateTime = timeMilliNow;
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

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Hat getCurrentHat() {
        return currentHat;
    }

    public void setCurrentHat(Hat currentHat) {
        this.currentHat = currentHat;
    }

    public Sessions getGooseSession() {
        return gooseSession;
    }

    public void setGooseSession(Sessions gooseSession) {
        this.gooseSession = gooseSession;
    }

    public int getGooseCoins() {
        return gooseCoins;
    }

    public void setGooseCoins(int gooseCoins) {
        this.gooseCoins = gooseCoins;
    }

    public int getGooseId() {
        return gooseId;
    }

    public void setGooseId(int gooseId) {
        this.gooseId = gooseId;
    }


//    private void starve() {
//        Date date = new Date();
//        long timeMilliNow = date.getTime();
//        currentHunger = (int)((long)currentHunger - (((timeMilliNow - lastUpdateTime)/10000) * 5));  //double
//        if(currentHunger <= 0) {
//            currentHunger = 0;
//            //shrink health
//        }
//        lastUpdateTime = timeMilliNow;
//    }


    private int reduceCharacteristic(int currentCharacteristic, long timeMilliNow) {
        int newCurrentCharacteristic = (int)((long)currentCharacteristic - (((timeMilliNow - lastUpdateTime)/10000) * 5));
        if(newCurrentCharacteristic <= 0) {
            newCurrentCharacteristic = 0;
            //shrink health
        }
        return newCurrentCharacteristic;
    }


    private void takeOffHat() {
        maxHunger = maxHunger - currentHat.getNutrition();
        maxHygiene = maxHygiene - currentHat.getHygieneBonus();
        maxSatisfaction = maxSatisfaction - currentHat.getSatisfactionBonus();
    }
}
