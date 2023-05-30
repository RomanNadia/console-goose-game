package com.goose.conection.bd.dao;

import com.goose.conection.bd.DBConection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FoodDao {

    public ResultSet getFood() throws Exception{
        DBConection dbConection = new DBConection();
        Connection con = dbConection.getConnection();

        Statement stmt = con.createStatement(); // чи краще перекидатись цим обєктом?
        ResultSet rs = stmt.executeQuery("SELECT foodName FROM food");
        return rs;
    }

}
