package com.goose.info.from.db;

import com.goose.conection.bd.dao.FoodDao;
import com.goose.models.Food;

import java.sql.SQLException;
import java.util.HashMap;

public class FoodsInfo {

    private static HashMap<String, Food> foods;


    public static synchronized HashMap<String, Food> getFoods() throws SQLException, ClassNotFoundException {

        if (foods == null)
            foods =  FoodsInfo.inicializeFoods();

        return foods;
    }


    private static HashMap<String, Food> inicializeFoods() throws SQLException, ClassNotFoundException {
        return FoodDao.getFoodDao().getFoods();
    }

}
