package com.goose.conection.bd.dao;

import com.goose.models.Detergent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DetergentDao extends Dao {
    private static DetergentDao detergentDao;

    private DetergentDao() throws SQLException, ClassNotFoundException {
    }

    public static synchronized DetergentDao getDetergentDao() throws SQLException, ClassNotFoundException {

        if (detergentDao == null)
            detergentDao = new DetergentDao();

        return detergentDao;
    }


    public HashMap<String, Detergent> getDetergents() throws SQLException {
        ResultSet rs = executeQuery("SELECT id, detergentName, washingLevel FROM detergent");
        HashMap<String, Detergent> detergents = new HashMap<String, Detergent>();

        while (rs.next()) {
            String id = rs.getString("id");
            String detergentName = rs.getString("detergentName");
            int washingLevel = rs.getInt("washingLevel");
            detergents.put(id, new Detergent(detergentName, washingLevel));
        }

        return detergents;
    }

}
