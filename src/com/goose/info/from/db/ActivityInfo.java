package com.goose.info.from.db;

import com.goose.conection.bd.dao.ActivityDao;
import com.goose.models.Activity;

import java.sql.SQLException;
import java.util.HashMap;

public class ActivityInfo {
    private static HashMap<String, Activity> activities;

    public static synchronized HashMap<String, Activity> getActivities() throws SQLException, ClassNotFoundException {

        if (activities == null)
            activities =  ActivityInfo.inicializeActivities();

        return activities;
    }


    private static HashMap<String, Activity> inicializeActivities() throws SQLException, ClassNotFoundException {
        return ActivityDao.getActivityDao().getActivities();
    }
}
