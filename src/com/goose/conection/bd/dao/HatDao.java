package com.goose.conection.bd.dao;

import com.goose.models.Food;
import com.goose.models.Hat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HatDao extends Dao {

    public HatDao() throws SQLException, ClassNotFoundException {
    }

    public HashMap<String, Hat> getHats() throws SQLException {
        ResultSet rs = executeQuery("SELECT id, hatName, nutrition, washingLevel, satisfaction FROM hat");
        HashMap<String, Hat> hats = new HashMap<String, Hat>();

        while (rs.next()) {
            String id = rs.getString("id");
            String hatName = rs.getString("hatName");
            int nutrition = rs.getInt("nutrition");
            int washingLevel = rs.getInt("washingLevel");
            int satisfaction = rs.getInt("satisfaction");
            hats.put(id, new Hat(hatName, nutrition, washingLevel, satisfaction));
        }

        return hats;
    }


    public void insertHat(Hat hat) throws SQLException {
        insert("INSERT INTO hat (hatName, nutrition, washingLevel, satisfaction) VALUE ('" + hat.getName() + "', " +
                hat.getNutrition() + ", " +  hat.getWashingLevel() + ", " + hat.getSatisfaction() + ")");
    }

}
