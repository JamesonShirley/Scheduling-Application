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
    public static void customers() throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT c.Customer_ID, c.Customer_Name, c.Address, f.Division, co.Country, c.Postal_Code, c.Phone\n" +
                "FROM customers c\n" + "INNER JOIN first_level_divisions f ON f.Division_ID = c.Division_ID\n" +
                "INNER JOIN countries co ON co.Country_ID = f.Country_ID",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()){
            CustList.addCust(new Customer(results.getInt("Customer_ID"),
                    results.getString("Customer_Name"), results.getString("Address"), results.getString("Division"), results.getString("Country"), results.getString("Postal_Code"), results.getString("Phone")));
        }
        temp.closeConnection();
    }
}

