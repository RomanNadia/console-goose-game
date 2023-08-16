package com.goose.conection.bd.dao;

import com.goose.models.Hat;
import com.goose.models.Sessions;
import com.mysql.cj.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SessionDao extends Dao {

    private static SessionDao sessionDao;

    private SessionDao() throws SQLException, ClassNotFoundException {
    }

    public static synchronized SessionDao getSessionDao() throws SQLException, ClassNotFoundException {

        if (sessionDao == null)
            sessionDao = new SessionDao();

        return sessionDao;
    }

    public HashMap<String, Sessions> getSessionsNames() throws SQLException {
        ResultSet rs = executeQuery("SELECT sessionName FROM sessions");
        HashMap<String, Sessions> sessionsNames = new HashMap<String, Sessions>();
        int i = 1;

        while (rs.next()) {
            String sessionName = rs.getString("sessionName");
            if(!sessionName.equals("DEFAULT")) {
                sessionsNames.put(String.valueOf(i), new Sessions(sessionName));
                i++;
            }
        }

        return sessionsNames;
    }


    public void insertSession(Sessions session) throws SQLException {
        upsert("INSERT INTO sessions VALUE ('" + session.getSessionName() + "')");
    }


}
