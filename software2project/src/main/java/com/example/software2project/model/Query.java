package com.example.software2project.model;

import com.example.software2project.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    public static Boolean login(String user, String pass) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT *\n" +
                "FROM users\n" + "WHERE User_Name = ?\n" + "AND Password = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, user);
        temp.getPreparedStatement().setString(2, pass);
        ResultSet results = temp.getPreparedStatement().executeQuery();
        int count = 0;
        while (results.next()){count++;}
        temp.closeConnection();
        return count == 1;
    }
}
