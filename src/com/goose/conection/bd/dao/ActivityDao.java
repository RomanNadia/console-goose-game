package com.goose.conection.bd.dao;

import com.goose.models.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ActivityDao extends Dao {
    private static ActivityDao activityDao;

    private ActivityDao() throws SQLException, ClassNotFoundException {
    }

    public static synchronized ActivityDao getActivityDao() throws SQLException, ClassNotFoundException {

        if (activityDao == null)
            activityDao = new ActivityDao();

        return activityDao;
    }


    public HashMap<String, Activity> getActivities() throws SQLException {
        ResultSet rs = executeQuery("SELECT id, activityName, satisfaction FROM activities");
        HashMap<String, Activity> activities = new HashMap<String, Activity>();

        while (rs.next()) {
            String id = rs.getString("id");
            String activityName = rs.getString("activityName");
            int satisfaction = rs.getInt("satisfaction");
            activities.put(id, new Activity(activityName, satisfaction));
        }

        return activities;
    }
}
