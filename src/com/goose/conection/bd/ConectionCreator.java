package com.goose.conection.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectionCreator {
    private static String url = "jdbc:mysql://localhost:3306/gooseGame";
    private static Connection conection;

    private static Connection initConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,"root","7985");

        return con;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conection == null) {
            conection = initConnection();
        }
        return conection;
    }





}
