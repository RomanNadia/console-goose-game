package com.goose.models;

import com.goose.conection.bd.dao.Dao;

import java.sql.SQLException;
import java.util.Objects;

public class Hat {
    private String name;
    private int hungerBonus;
    private int hygieneBonus;
    private int satisfactionBonus;

    public Hat(String name, int hungerBonus, int hygieneBonus, int satisfactionBonus) {
        this.name = name;
        this.hungerBonus = hungerBonus;
        this.hygieneBonus = hygieneBonus;
        this.satisfactionBonus = satisfactionBonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNutrition() {
        return hungerBonus;
    }

    public void setNutrition(int nutrition) {
        this.hungerBonus = nutrition;
    }

    public int getHygieneBonus() {
        return hygieneBonus;
    }

    public void setHygieneBonus(int hygieneBonus) {
        this.hygieneBonus = hygieneBonus;
    }

    public int getSatisfactionBonus() {
        return satisfactionBonus;
    }

    public void setSatisfactionBonus(int satisfactionBonus) {
        this.satisfactionBonus = satisfactionBonus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hat hat = (Hat) o;
        return name.equals(hat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }



}


