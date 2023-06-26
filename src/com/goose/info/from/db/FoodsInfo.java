package com.goose.info.from.db;

import com.goose.conection.bd.dao.FoodDao;
import com.goose.models.Food;

import java.sql.SQLException;
import java.util.HashMap;

public class FoodsInfo {

    private static HashMap<String, Food> foods;

    static {
        try {
            foods = FoodsInfo.inicializeFoods();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Food> getFoods() {
        return foods;
    }

    private static HashMap<String, Food> inicializeFoods() throws SQLException, ClassNotFoundException {
        FoodDao foodDao = new FoodDao();
        return foodDao.getFoods();
    }
}
