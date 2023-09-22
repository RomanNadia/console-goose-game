package com.goose.info.from.db;

import com.goose.conection.bd.dao.HatDao;
import com.goose.models.Hat;
import com.goose.models.Sessions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HatsInfo {

    private static HashMap<String, Hat> availableHats;
    private static HashMap<String, Hat> availableHatsToBay;          //available to bay
    //maybe available hats

    public static synchronized HashMap<String, Hat> getAvailableHats(int gooseId)
            throws SQLException, ClassNotFoundException {

        if (availableHats == null)
            availableHats =  HatsInfo.inicializeAvailableHats(gooseId);

        return availableHats;
    }

    public static synchronized HashMap<String, Hat> getAvailableHatsToBay(Sessions session, int gooseId)
            throws SQLException, ClassNotFoundException {

        if (availableHatsToBay == null)
            availableHatsToBay =  HatsInfo.inicializeAvailableHatsToBay(session, gooseId);

        return availableHatsToBay;
    }


    private static HashMap<String, Hat> inicializeAvailableHats(int gooseId) throws SQLException, ClassNotFoundException {
        return HatDao.getHatDao().getAvailableHats(gooseId);
    }


    private static HashMap<String, Hat> inicializeAvailableHatsToBay(Sessions session, int gooseId)
            throws SQLException, ClassNotFoundException {
        return HatDao.getHatDao().getAvailableHatsToBay(session, gooseId);
    }


    public static void addNewHatToAvailableHatsHashMap(Hat hat) {
        availableHats.put(String.valueOf(availableHats.size()),hat);
    }

    public static void addAvailableHatsToBayToAvailableHatsHashMap(Hat hat) {
        availableHats.put(String.valueOf(availableHats.size()),hat);
        deleteFromAvailableHatsToBay(hat);
    }

    private static void deleteFromAvailableHatsToBay(Hat hat) {
        HashMap<String, Hat> newSessionHats = new HashMap<String, Hat>();
        int i = 1;
        for(Map.Entry<String, Hat> entry: availableHatsToBay.entrySet()) {
            Hat entryValue = entry.getValue();
            if (entryValue != hat) {
                newSessionHats.put(String.valueOf(i),entryValue);
                i++;
            }
        }
        availableHatsToBay = newSessionHats;
    }


}
