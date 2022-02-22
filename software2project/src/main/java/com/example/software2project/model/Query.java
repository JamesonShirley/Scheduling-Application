package com.example.software2project.model;

import com.example.software2project.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
    public static void apptMonth() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()){
            ZonedDateTime start = ZonedDateTime.ofInstant(results.getTimestamp("Start").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
            String startString = start.format(formatter);
            ZonedDateTime end = ZonedDateTime.ofInstant(results.getTimestamp("End").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
            String endString = end.format(formatter);
            ApptList.addAppt(new Appointment(results.getInt("Appointment_ID"),
                    results.getString("Title"), results.getString("Description"), results.getString("Location"), results.getString("Contact_Name"), results.getString("Type"), startString, endString, results.getInt("Customer_ID"), results.getInt("User_ID")));
        }
        temp.closeConnection();
    }
    //TODO add monthly and weekly options


    public static void addCust(String name, String address, String code, String phone, int divId) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID)\n" +
                "VALUES(?,?,?,?,?)",temp.getConnection());
        temp.getPreparedStatement().setString(1, name);
        temp.getPreparedStatement().setString(2, address);
        temp.getPreparedStatement().setString(3, code);
        temp.getPreparedStatement().setString(4, phone);
        temp.getPreparedStatement().setInt(5, divId);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }
    public static ObservableList<String> getCountries() throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT Country\n" +
                "FROM countries",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        ObservableList <String> arr = FXCollections.observableArrayList();
        while (results.next()){
            arr.add(results.getString("Country"));
        }
        temp.closeConnection();
        return arr;
    }

    public static ObservableList<String> getDivisions(String country) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT f.Division\n" +
                "FROM first_level_divisions f\n" + "INNER JOIN countries c ON c.Country_ID = f.Country_ID\n" +
                "WHERE c.Country = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, country);
        ResultSet results = temp.getPreparedStatement().executeQuery();
        ObservableList <String> arr = FXCollections.observableArrayList();
        while (results.next()){
            arr.add(results.getString("Division"));
        }
        temp.closeConnection();
        return arr;
    }
    public static int getDivId(String div) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT Division_ID\n" +
                "FROM first_level_divisions\n" + "WHERE Division = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, div);
        ResultSet results = temp.getPreparedStatement().executeQuery();
        int value = 0;
        while (results.next()) {
            value = results.getInt("Division_ID");
        }
        temp.closeConnection();
        return value;
    }
}

