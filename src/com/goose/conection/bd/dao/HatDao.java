package com.goose.conection.bd.dao;

import com.goose.models.Goose;
import com.goose.models.Hat;
import com.goose.models.Sessions;

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
    public HashMap<String, Hat> getHats(Sessions session) throws SQLException {
        ResultSet rsOfDefaultHats = executeQuery("SELECT id, hatName, hungerBonus, hygieneBonus, satisfactionBonus " +
                "FROM hat WHERE sessionName = 'DEFAULT'");

        HashMap<String, Hat> hats = new HashMap<String, Hat>();
        putHatsToHashMapFromResultSet(rsOfDefaultHats, hats, 1);

        ResultSet rsOfCustomHatsOfGoose = executeQuery("SELECT id, hatName, hungerBonus, hygieneBonus, satisfactionBonus " +
                "FROM hat WHERE sessionName = '" + session.getSessionName() + "'");

        putHatsToHashMapFromResultSet(rsOfCustomHatsOfGoose, hats, hats.size() + 1);

        return hats;
    }


    private void putHatsToHashMapFromResultSet(ResultSet rs, HashMap<String, Hat> hats, int i) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            String hatName = rs.getString("hatName");
            int hungerBonus = rs.getInt("hungerBonus");
            int hygieneBonus = rs.getInt("hygieneBonus");
            int satisfactionBonus = rs.getInt("satisfactionBonus");
            hats.put(Integer.toString(i), new Hat(id, hatName, hungerBonus, hygieneBonus, satisfactionBonus));
            i++;
        }
    }


//    public Hat getHatById(String id) throws SQLException {
//        ResultSet rs = executeQuery("SELECT * FROM hat WHERE id = " + id);
//        return new Hat(rs.getString("hatName"), rs.getInt("hungerBonus"),
//                rs.getInt("hygieneBonus"), rs.getInt("satisfactionBonus"));
//    }


    public void insertHat(Hat hat, Sessions session) throws SQLException {
        upsert("INSERT INTO hat (hatName, hungerBonus, hygieneBonus, satisfactionBonus, sessionName) VALUE ('"
                + hat.getName() + "', " + hat.getNutrition() + ", " +  hat.getHygieneBonus() + ", "
                + hat.getSatisfactionBonus() + ", '" + session.getSessionName() + "' )");
    }


    public int findHatIdByName(String hatName) throws SQLException {
        ResultSet rs = executeQuery("SELECT id from hat WHERE hatName = '" + hatName + "'");
        rs.next();
        return rs.getInt("id");
    }


}



