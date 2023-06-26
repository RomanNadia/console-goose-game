package com.goose.conection.bd.dao;

import com.goose.conection.bd.ConectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class Dao {

    private Connection connection;

    protected Dao() throws SQLException, ClassNotFoundException {
        this.connection = ConectionCreator.getConnection();;
    }

    protected ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    protected void insert(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }
}
