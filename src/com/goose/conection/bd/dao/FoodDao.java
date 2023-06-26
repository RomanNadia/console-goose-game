package com.goose.conection.bd.dao;

import com.goose.conection.bd.ConectionCreator;
import com.goose.exceptions.IdNotFoundException;
import com.goose.models.Food;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class FoodDao extends Dao {


    public FoodDao() throws SQLException, ClassNotFoundException {
    }

    public HashMap<String, Food> getFoods() throws SQLException {
        ResultSet rs = executeQuery("SELECT id, foodName, nutrition FROM food");
        HashMap<String, Food> foods = new HashMap<String, Food>();

        while (rs.next()) {
            String id = rs.getString("id");
            String foodName = rs.getString("foodName");
            int nutrition = rs.getInt("nutrition");
            foods.put(id, new Food(foodName, nutrition));
        }

        return foods;
    }


}
