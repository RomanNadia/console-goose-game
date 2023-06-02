package com.goose.conection.bd.dao;

import com.goose.conection.bd.ConectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FoodDao {

    private Connection connection;

    public FoodDao() throws Exception {
        this.connection = ConectionCreator.getConnection();;
    }

    public ResultSet getFood() throws Exception {
        Statement stmt = connection.createStatement(); // чи краще перекидатись цим обєктом?
        ResultSet rs = stmt.executeQuery("SELECT foodName FROM food");
        return rs;
    }

}
