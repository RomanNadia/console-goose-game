package com.goose.conection.bd.dao;

import com.goose.models.Goose;
import com.goose.models.Hat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HatDao extends Dao {

    private static HatDao hatDao;

    private HatDao() throws SQLException, ClassNotFoundException {
    }


    public static synchronized HatDao getHatDao() throws SQLException, ClassNotFoundException {

        if (hatDao == null)
            hatDao = new HatDao();

        return hatDao;
    }


    //key is not id!!!
    public HashMap<String, Hat> getHats(String gooseName) throws SQLException {
        ResultSet rsOfDefaultHats = executeQuery("SELECT id, hatName, hungerBonus, hygieneBonus, satisfactionBonus FROM hat WHERE " +
                "gooseName = 'DEFAULT'");

        HashMap<String, Hat> hats = new HashMap<String, Hat>();

        int i = 1;

        while (rsOfDefaultHats.next()) {
            int id = rsOfDefaultHats.getInt("id");
            String hatName = rsOfDefaultHats.getString("hatName");
            int hungerBonus = rsOfDefaultHats.getInt("hungerBonus");
            int hygieneBonus = rsOfDefaultHats.getInt("hygieneBonus");
            int satisfactionBonus = rsOfDefaultHats.getInt("satisfactionBonus");
            hats.put(Integer.toString(i), new Hat(id, hatName, hungerBonus, hygieneBonus, satisfactionBonus));
            i++;
        }


        ResultSet rsOfCustomHatsOfGoose = executeQuery("SELECT id, hatName, hungerBonus, hygieneBonus, satisfactionBonus " +
                "FROM hat WHERE gooseName = '" + gooseName + "'");


        while (rsOfCustomHatsOfGoose.next()) {
            int id = rsOfCustomHatsOfGoose.getInt("id");
            String hatName = rsOfCustomHatsOfGoose.getString("hatName");
            int hungerBonus = rsOfCustomHatsOfGoose.getInt("hungerBonus");
            int hygieneBonus = rsOfCustomHatsOfGoose.getInt("hygieneBonus");
            int satisfactionBonus = rsOfCustomHatsOfGoose.getInt("satisfactionBonus");
            hats.put(Integer.toString(i), new Hat(id, hatName, hungerBonus, hygieneBonus, satisfactionBonus));
            i++;
        }

        return hats;
    }


//    public Hat getHatById(String id) throws SQLException {
//        ResultSet rs = executeQuery("SELECT * FROM hat WHERE id = " + id);
//        return new Hat(rs.getString("hatName"), rs.getInt("hungerBonus"),
//                rs.getInt("hygieneBonus"), rs.getInt("satisfactionBonus"));
//    }


    public void insertHat(Hat hat, String gooseName) throws SQLException {
        upsert("INSERT INTO hat (hatName, hungerBonus, hygieneBonus, satisfactionBonus, gooseName) VALUE ('" + hat.getName() + "', " +
                hat.getNutrition() + ", " +  hat.getHygieneBonus() + ", " + hat.getSatisfactionBonus() + ", '" + gooseName + "' )");
    }


    public int findHatIdByName(String hatName) throws SQLException {
        ResultSet rs = executeQuery("SELECT id from hat WHERE hatName = '" + hatName + "'");
        rs.next();
        int id = rs.getInt("id");
        return id;
    }


}



