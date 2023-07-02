package com.goose.conection.bd.dao;

import com.goose.models.Goose;
import com.goose.models.Hat;

import java.sql.SQLException;

public class GooseDao extends Dao {

    public GooseDao() throws SQLException, ClassNotFoundException {
    }

    public void upsertGoose(Goose goose) throws SQLException {
        //chosing a correct method based on fact is goose alive
        insertGoose(goose);
    }


    private void insertGoose(Goose goose) throws SQLException {
        upsert("INSERT INTO goose (gooseName, maxHunger, currentHunger, maxHygiene, currentHygiene, " +
                "maxSatisfaction, currentSatisfaction, maxHealth, currentHealth, lastUpdateTime, currentHatId) VALUE ('"
                + goose.getName() + "', " + goose.getMaxHunger() + ", " +  goose.getCurrentHunger() + ", "
                + goose.getMaxHygiene() + goose.getCurrentHygiene() + ", " +  goose.getMaxSatisfaction() + ", "
                + goose.getCurrentSatisfaction() + ", " + goose.getMaxHealth() + ", " +  goose.getCurrentHealth() + ", "
                + goose.getLastUpdateTime() + ", " + goose.getCurrentHat() + ")");
    }


    private void updateGoose(Goose goose) throws SQLException {
        upsert("UPDATE goose SET maxHunger = " + goose.getMaxHunger() + ", currentHunger = "
                + goose.getMaxHunger() + ", maxHygiene = " + goose.getMaxHygiene() + ", currentHygiene = "
                + goose.getCurrentHygiene() + ", maxSatisfaction = " + goose.getMaxSatisfaction()
                + ", currentSatisfaction = " + goose.getCurrentSatisfaction() + ", maxHealth = " + goose.getMaxHealth()
                + ", currentHealth = " +  goose.getCurrentHealth() + ", lastUpdateTime = " + goose.getLastUpdateTime()
                + ", currentHatId = " + goose.getCurrentHat() + "WHERE gooseName = " + goose.getName() + ")");
    }


}
