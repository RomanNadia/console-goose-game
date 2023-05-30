package com.goose.conection.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConection {
    String url = "jdbc:mysql://localhost:3306/gooseGame";

    public Connection getConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,"root","7985");

        return con;
    }




}
