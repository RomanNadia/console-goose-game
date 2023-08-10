package com.goose.info.from.db;

import com.goose.conection.bd.dao.FoodDao;
import com.goose.conection.bd.dao.HatDao;
import com.goose.models.Food;
import com.goose.models.Hat;

import java.sql.SQLException;
import java.util.HashMap;

public class HatsInfo {

    private static HashMap<String, Hat> hats;
    //maybe available hats

    public static synchronized HashMap<String, Hat> getHats() throws SQLException, ClassNotFoundException {

        if (hats == null)
            hats =  HatsInfo.inicializeHats();

        return hats;
    }


    private static HashMap<String, Hat> inicializeHats() throws SQLException, ClassNotFoundException {
        return HatDao.getHatDao().getHats();
    }

}
