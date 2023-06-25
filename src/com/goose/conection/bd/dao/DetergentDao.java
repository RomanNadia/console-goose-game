package com.goose.conection.bd.dao;

import com.goose.conection.bd.ConectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetergentDao {
    private Connection connection;

    public DetergentDao() throws SQLException, ClassNotFoundException {
        this.connection = ConectionCreator.getConnection();;
    }

    public ResultSet getDetergent() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, detergentName FROM detergent");
        return rs;
    }

    public int getNutritionById(String id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT washingLevel FROM detergent WHERE id = " + id);
        int nutrition = rs.getInt("washingLevel");
        return nutrition;
    }
}
