package com.goose.conection.bd.dao;

import com.goose.models.Goose;
import com.goose.models.Hat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GooseDao extends Dao {

    public GooseDao() throws SQLException, ClassNotFoundException {
    }


    public HashMap<String, String> getGeeseNames() throws SQLException {
        ResultSet rs = executeQuery("SELECT gooseName FROM goose");
        HashMap<String, String> geeseNames = new HashMap<String, String>();
        int i = 1;

        while (rs.next()) {
            String gooseName = rs.getString("gooseName");
            geeseNames.put(String.valueOf(i) ,gooseName);
            i++;
        }

        return geeseNames;
    }


    public Goose getGooseByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery("SELECT * FROM goose WHERE gooseName = " + name);
        HatDao hatDao = new HatDao();
        return new Goose(rs.getString("gooseName"), rs.getInt("maxHunger"),
                rs.getInt("currentHunger"), rs.getInt("maxHygiene"),
                rs.getInt("currentHygiene"), rs.getInt("maxSatisfaction"),
                rs.getInt("currentSatisfaction"), rs.getInt("maxHealth"),
                rs.getInt("currentHealth"), rs.getLong("lastUpdateTime"),
                hatDao.getHatById(String.valueOf(rs.getInt("currentHatId"))));
    }


//    public void upsertGoose(Goose goose) throws SQLException {
//        //choosing a correct method based on fact is goose exists
//        insertGoose(goose);
//    }


    public void insertGoose(Goose goose) throws SQLException {
        upsert("INSERT INTO goose (gooseName, maxHunger, currentHunger, maxHygiene, currentHygiene, " +
                "maxSatisfaction, currentSatisfaction, maxHealth, currentHealth, lastUpdateTime) VALUE ('"
                + goose.getName() + "', " + goose.getMaxHunger() + ", " +  goose.getCurrentHunger() + ", "
                + goose.getMaxHygiene() + ", " + goose.getCurrentHygiene() + ", " +  goose.getMaxSatisfaction() + ", "
                + goose.getCurrentSatisfaction() + ", " + goose.getMaxHealth() + ", " +  goose.getCurrentHealth() + ", "
                + goose.getLastUpdateTime() + ")");
    }


    public void updateGoose(Goose goose) throws SQLException {
        upsert("UPDATE goose SET maxHunger = " + goose.getMaxHunger() + ", currentHunger = "
                + goose.getMaxHunger() + ", maxHygiene = " + goose.getMaxHygiene() + ", currentHygiene = "
                + goose.getCurrentHygiene() + ", maxSatisfaction = " + goose.getMaxSatisfaction()
                + ", currentSatisfaction = " + goose.getCurrentSatisfaction() + ", maxHealth = " + goose.getMaxHealth()
                + ", currentHealth = " +  goose.getCurrentHealth() + ", lastUpdateTime = " + goose.getLastUpdateTime()
                + ", currentHatId = " + goose.getCurrentHat() + "WHERE gooseName = " + goose.getName() + ")");
    }


}
