package com.goose.info.from.db;

import com.goose.conection.bd.dao.DetergentDao;
import com.goose.models.Detergent;

import java.sql.SQLException;
import java.util.HashMap;

public class DetergentInfo {
    private static HashMap<String, Detergent> detergents;

    public static synchronized HashMap<String, Detergent> getDetergents() throws SQLException, ClassNotFoundException {

        if (detergents == null)
            detergents =  DetergentInfo.inicializeDetergents();

        return detergents;
    }


    private static HashMap<String, Detergent> inicializeDetergents() throws SQLException, ClassNotFoundException {
        return DetergentDao.getDetergentDao().getDetergents();
    }
}
