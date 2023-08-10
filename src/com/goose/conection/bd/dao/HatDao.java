package com.goose.conection.bd.dao;

import com.goose.models.Goose;
import com.goose.models.Hat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HatDao extends Dao {

    public HatDao() throws SQLException, ClassNotFoundException {
    }

    public HashMap<String, Hat> getHats() throws SQLException {
        ResultSet rs = executeQuery("SELECT id, hatName, hungerBonus, hygieneBonus, satisfactionBonus FROM hat");
        HashMap<String, Hat> hats = new HashMap<String, Hat>();

        while (rs.next()) {
            String id = rs.getString("id");
            String hatName = rs.getString("hatName");
            int hungerBonus = rs.getInt("hungerBonus");
            int hygieneBonus = rs.getInt("hygieneBonus");
            int satisfactionBonus = rs.getInt("satisfactionBonus");
            hats.put(id, new Hat(hatName, hungerBonus, hygieneBonus, satisfactionBonus));
        }

        return hats;
    }


    public Hat getHatById(String id) throws SQLException {
        ResultSet rs = executeQuery("SELECT * FROM hat WHERE id = " + id);
        return new Hat(rs.getString("hatName"), rs.getInt("hungerBonus"),
                rs.getInt("hygieneBonus"), rs.getInt("satisfactionBonus"));
    }


    public void insertHat(Hat hat) throws SQLException {
        upsert("INSERT INTO hat (hatName, hungerBonus, hygieneBonus, satisfactionBonus) VALUE ('" + hat.getName() + "', " +
                hat.getNutrition() + ", " +  hat.getHygieneBonus() + ", " + hat.getSatisfactionBonus() + ")");
    }


}



