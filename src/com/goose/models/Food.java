package com.goose.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Food  {
    private String name;
    private int nutrition;

    public Food(String name, int nutrition) {
        this.name = name;
        this.nutrition = nutrition;
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

//    static public int getNutritionById (String id, HashMap<String, Food> foods) {
//        return foods.get(id).getNutrition(); // needs check
//    }

}
