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


//    key is not id!!!
    public HashMap<String, Hat> getSessionHats(Sessions session, int gooseId) throws SQLException {
        ResultSet rsOfDefaultHats = executeQuery("SELECT hat.id, hat.hatName, hat.hungerBonus, hat.hygieneBonus, " +
                "hat.satisfactionBonus FROM hat WHERE hat.sessionName = '" + session.getSessionName()
                + "' AND hat.id NOT IN (SELECT hat.id FROM hat INNER JOIN hat_goose ON hat.id = hat_goose.hatId " +
                "WHERE hat_goose.gooseId = " + gooseId + ")");

        HashMap<String, Hat> hats = new HashMap<String, Hat>();
        putHatsToHashMapFromResultSet(rsOfDefaultHats, hats, 1);

        return hats;
    }


    public HashMap<String, Hat> getAvailableHats(int gooseId) throws SQLException {
        ResultSet rsOfDefaultHats = executeQuery("SELECT id, hatName, hungerBonus, hygieneBonus, satisfactionBonus " +
                "FROM hat WHERE sessionName = 'DEFAULT'");

        HashMap<String, Hat> hats = new HashMap<String, Hat>();
        putHatsToHashMapFromResultSet(rsOfDefaultHats, hats, 1);

        ResultSet rsOfCustomHatsOfGoose = executeQuery("SELECT hat.id, hat.hatName, hat.hungerBonus, hat.hygieneBonus, " +
                "hat.satisfactionBonus FROM hat INNER JOIN hat_goose ON hat.id = hat_goose.hatId " +
                "WHERE hat_goose.gooseId = " + gooseId + "");

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


    public int findHatIdByName(String hatName, Sessions session) throws SQLException {
        ResultSet rs = executeQuery("SELECT id from hat WHERE hatName = '" + hatName + "' and sessionName = '"
                + session.getSessionName() + "'");
        rs.next();
        return rs.getInt("id");
    }


    //goose_hat
    public void insertGooseHat(Hat hat, Goose goose) throws SQLException {
        upsert("INSERT INTO hat_goose (gooseId, hatId) VALUE ("
                + goose.getGooseId() + ", " + hat.getId()  + " )");
    }


    public boolean chekIfHatNameExistInSession(String name, Sessions session) throws SQLException {
        ResultSet rs = executeQuery("SELECT * FROM hat WHERE hatName = '" + name + "' And (sessionName = '"
                + session.getSessionName() + "' OR sessionName = 'DEFAULT')");
        return rs.next();
    }

}



