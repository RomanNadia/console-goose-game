package com.goose.info.from.db;

import com.goose.conection.bd.dao.HatDao;
import com.goose.models.Goose;
import com.goose.models.Hat;
import com.goose.models.Sessions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HatsInfo {

    private static HashMap<String, Hat> availableHats;
    private static HashMap<String, Hat> sessionHats;
    //maybe available hats

    public static synchronized HashMap<String, Hat> getAvailableHats(int gooseId)
            throws SQLException, ClassNotFoundException {

        if (availableHats == null)
            availableHats =  HatsInfo.inicializeAvailableHats(gooseId);

        return availableHats;
    }

    public static synchronized HashMap<String, Hat> getSessionHats(Sessions session, int gooseId)
            throws SQLException, ClassNotFoundException {

        if (sessionHats == null)
            sessionHats =  HatsInfo.inicializeSessionHats(session, gooseId);

        return sessionHats;
    }


    private static HashMap<String, Hat> inicializeAvailableHats(int gooseId) throws SQLException, ClassNotFoundException {
        return HatDao.getHatDao().getAvailableHats(gooseId);
    }


    private static HashMap<String, Hat> inicializeSessionHats(Sessions session, int gooseId)
            throws SQLException, ClassNotFoundException {
        return HatDao.getHatDao().getSessionHats(session, gooseId);
    }


    public static void addNewHatToAvailableHatsHashMap(Hat hat) {
        availableHats.put(String.valueOf(availableHats.size()),hat);
    }

    public static void addSessionHatToAvailableHatsHashMap(Hat hat) {
        availableHats.put(String.valueOf(availableHats.size()),hat);
        deleteFromSessionHats(hat);
    }

    private static void deleteFromSessionHats(Hat hat) { ///maybe just check data from bd
        HashMap<String, Hat> newSessionHats = new HashMap<String, Hat>();
        int i = 1;
        for(Map.Entry<String, Hat> entry: sessionHats.entrySet()) {
            Hat entryValue = entry.getValue();
            if (entryValue != hat) {
                newSessionHats.put(String.valueOf(i),entryValue);
                i++;
            }
        }
        sessionHats = newSessionHats;
    }


}
