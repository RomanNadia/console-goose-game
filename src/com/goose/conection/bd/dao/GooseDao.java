package com.goose.conection.bd.dao;

import com.goose.info.from.db.HatsInfo;
import com.goose.models.Goose;
import com.goose.models.Hat;
import com.goose.models.Sessions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

public class GooseDao extends Dao {

    public GooseDao() throws SQLException, ClassNotFoundException {
    }


    public HashMap<String, String> getGeeseNames(Sessions session) throws SQLException {
        ResultSet rs = executeQuery("SELECT gooseName FROM goose WHERE sessionName = '" + session.getSessionName() + "'");
        HashMap<String, String> geeseNames = new HashMap<String, String>();
        int i = 1;

        while (rs.next()) {
            String gooseName = rs.getString("gooseName");
            if(!gooseName.equals("DEFAULT")) {
                geeseNames.put(String.valueOf(i), gooseName);
                i++;
            }
        }

        return geeseNames;
    }


    public Goose getGooseByNameInSuchSession(String name, Sessions session) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery("SELECT id, maxHunger, currentHunger, maxHygiene, currentHygiene, maxSatisfaction, " +
                "currentSatisfaction, maxHealth, currentHealth, lastUpdateTime, currentHatId, gooseCoins FROM goose WHERE " +
                "gooseName = '" + name + "' AND sessionName = '" + session.getSessionName() + "'");
        rs.next();
        int gooseId = rs.getInt("id");
        return new Goose(gooseId, name, rs.getInt("maxHunger"),
                rs.getInt("currentHunger"), rs.getInt("maxHygiene"),
                rs.getInt("currentHygiene"), rs.getInt("maxSatisfaction"),
                rs.getInt("currentSatisfaction"), rs.getInt("maxHealth"),
                rs.getInt("currentHealth"), rs.getLong("lastUpdateTime"),
                getCurrentHat(rs.getInt("currentHatId"), gooseId), rs.getInt("gooseCoins"));
    }


    public void insertGoose(Goose goose, Sessions session) throws SQLException {
        upsert("INSERT INTO goose (gooseName, maxHunger, currentHunger, maxHygiene, currentHygiene, " +
                "maxSatisfaction, currentSatisfaction, maxHealth, currentHealth, lastUpdateTime, sessionName) VALUE ('"
                + goose.getName() + "', " + goose.getMaxHunger() + ", " +  goose.getCurrentHunger() + ", "
                + goose.getMaxHygiene() + ", " + goose.getCurrentHygiene() + ", " +  goose.getMaxSatisfaction() + ", "
                + goose.getCurrentSatisfaction() + ", " + goose.getMaxHealth() + ", " +  goose.getCurrentHealth() + ", "
                + goose.getLastUpdateTime() + ", '" + session.getSessionName() + "')");
    }


    public void updateGoose(Goose goose) throws SQLException, ClassNotFoundException {
        upsert("UPDATE goose SET maxHunger = " + goose.getMaxHunger() + ", currentHunger = "
                + goose.getCurrentHunger() + ", maxHygiene = " + goose.getMaxHygiene() + ", currentHygiene = "
                + goose.getCurrentHygiene() + ", maxSatisfaction = " + goose.getMaxSatisfaction()
                + ", currentSatisfaction = " + goose.getCurrentSatisfaction() + ", maxHealth = " + goose.getMaxHealth()
                + ", currentHealth = " +  goose.getCurrentHealth() + ", lastUpdateTime = " + goose.getLastUpdateTime()
                + ", currentHatId = " + goose.getCurrentHat().getId() + " WHERE gooseName = '" + goose.getName() + "'");
    }


    private Hat getCurrentHat(int HatId, int gooseId) throws SQLException, ClassNotFoundException {
        HashMap<String, Hat> hats = HatsInfo.getAvailableHats(gooseId);
        Hat currentHat = hats.get("1");

        for(Entry<String, Hat> entry: hats.entrySet()) {
            if(entry.getValue().getId() == HatId) {
                currentHat = entry.getValue();
                break;
            }
        }

        return currentHat;
    }


    public boolean chekIfGooseNameExist(String name, Sessions session) throws SQLException {
        ResultSet rs = executeQuery("SELECT * FROM goose WHERE gooseName = '" + name + "' And sessionName = '"
                + session.getSessionName() + "'");
        return rs.next();
    }


    public int findGooseId(Goose goose, Sessions session) throws SQLException {
        ResultSet rs = executeQuery("SELECT id from goose WHERE gooseName = '" + goose.getName() + "' and sessionName = '"
                + session.getSessionName() + "'");
        rs.next();
        return rs.getInt("id");
    }


}
