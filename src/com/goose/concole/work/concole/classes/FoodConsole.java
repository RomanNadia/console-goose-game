package com.goose.concole.work.concole.classes;

import com.goose.info.from.db.FoodsInfo;
import com.goose.models.Food;

import java.sql.SQLException;
import java.util.HashMap;

public class FoodConsole extends BaseConsole {

    public Food chooseFood() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose a food:");
        HashMap<String, Food> foods = FoodsInfo.getFoods();

        foods.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = getCorrectAction("", foods.size());

        return foods.get(input);
    }

}
