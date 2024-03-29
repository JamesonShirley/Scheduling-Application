package com.example.software2project.model;

import com.example.software2project.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class Query
 */

/**
 * @author Jameson Shirley
 */
public class Query {
    /**
     * returns true if username and password are valid
     * @param user username
     * @param pass password
     * @return boolean
     * @throws SQLException if there is a sql error
     */
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

    /**
     * Creates all customers and populates them into the customer list
     * @throws SQLException if there is a sql error
     */
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

    /***
     * lambda expression is used here. A lambda is used in order to convert my Zoned Date Time objects into Time and Date strings to be displayed in my appointment table.
     * populates the list of appointments without filters
     * @throws SQLException if there is a sql error
     */
    public static void allAppt() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()){
            ZonedDateTime start = ZonedDateTime.ofInstant(results.getTimestamp("Start").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
            ZonedDateTime end = ZonedDateTime.ofInstant(results.getTimestamp("End").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
            ArrayList<ZonedDateTime> timeStrings = new ArrayList<ZonedDateTime>();
            timeStrings.add(start);
            timeStrings.add(end);
            ArrayList<String> myTimes = new ArrayList<String>();
            timeStrings.forEach((t) -> myTimes.add(t.format(formatter)));
            ApptList.addAppt(new Appointment(results.getInt("Appointment_ID"),
                    results.getString("Title"), results.getString("Description"), results.getString("Location"), results.getString("Contact_Name"), results.getString("Type"),
                    myTimes.get(0), myTimes.get(1), results.getInt("Customer_ID"), results.getInt("User_ID")));
        }
        temp.closeConnection();
    }

    /***
     * lambda expression used here. A lambda is used here on a ArrayList of ZonedDateTime objects to convert them to Strings in order to save them in an Appointment class to be displayed in the future.
     * populates the list of appintments and filters by current month
     * @throws SQLException if there is a sql error
     */
    public static void monthAppt() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID\n" + "WHERE MONTH(start) = MONTH(CURDATE())",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()){
            ZonedDateTime start = ZonedDateTime.ofInstant(results.getTimestamp("Start").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
            ZonedDateTime end = ZonedDateTime.ofInstant(results.getTimestamp("End").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
            ArrayList<ZonedDateTime> timeStrings = new ArrayList<ZonedDateTime>();
            timeStrings.add(start);
            timeStrings.add(end);
            ArrayList<String> myTimes = new ArrayList<String>();
            timeStrings.forEach((t) -> myTimes.add(t.format(formatter)));
            ApptList.addAppt(new Appointment(results.getInt("Appointment_ID"),
                    results.getString("Title"), results.getString("Description"), results.getString("Location"), results.getString("Contact_Name"), results.getString("Type"), myTimes.get(0), myTimes.get(1), results.getInt("Customer_ID"), results.getInt("User_ID")));
        }
        temp.closeConnection();
    }

    /**
     * populates the list of appointments and filters by current week
     * @throws SQLException if there is a sql error
     */
    public static void weekAppt() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID\n" + "WHERE WEEK(start) = WEEK(CURDATE())",temp.getConnection());
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
    public static void searchAppt(String search) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID\n" + "WHERE a.Title LIKE concat('%',  concat(?, '%'))",temp.getConnection());
        temp.getPreparedStatement().setString(1, search);
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
    /**
     * populates the appointment list with all appointments for a given user
     * @param userID User ID
     * @throws SQLException if there is a sql error
     */
    public static void userAppt(int userID) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID\n" + "WHERE a.contact_ID = ?",temp.getConnection());
        temp.getPreparedStatement().setInt(1, userID);
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

    /**
     * adds a customer to the database
     * @param name name
     * @param address address
     * @param code code
     * @param phone phone number
     * @param divId division id
     * @throws SQLException if there is a sql error
     */
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

    /**
     * updates a customer in the database
     * @param id id
     * @param name name
     * @param address address
     * @param code code
     * @param phone phone number
     * @param divId division id
     * @throws SQLException if there is a sql error
     */
    public static void updateCust(int id, String name, String address, String code, String phone, int divId) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("UPDATE customers\n" +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?\n" +
                "WHERE Customer_ID = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, name);
        temp.getPreparedStatement().setString(2, address);
        temp.getPreparedStatement().setString(3, code);
        temp.getPreparedStatement().setString(4, phone);
        temp.getPreparedStatement().setInt(5, divId);
        temp.getPreparedStatement().setInt(6, id);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }

    /**
     * returns a list of all the countries in the database
     * @return list of countries
     * @throws SQLException if there is a sql error
     */
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

    /**
     * returns a list of all the divisions in the database for a given country
     * @param country country
     * @return division list
     * @throws SQLException if there is a sql error
     */
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

    /**
     * returns a divisions id
     * @param div division
     * @return division ID
     * @throws SQLException if there is a sql error
     */
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

    /**
     * returns a country id
     * @param country country
     * @return country id
     * @throws SQLException if there is a sql error
     */
    public static int getCountryId(String country) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT Country_ID\n" +
                "FROM countries\n" + "WHERE Country = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, country);
        ResultSet results = temp.getPreparedStatement().executeQuery();
        int value = 0;
        while (results.next()) {
            value = results.getInt("Country_ID");
        }
        temp.closeConnection();
        return value;
    }

    /**
     * deletes a customer from the database
     * @param id id
     * @throws SQLException if there is a sql error
     */
    public static void deleteCust(int id) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("DELETE FROM customers\n" +
                "WHERE Customer_ID = ?",temp.getConnection());
        temp.getPreparedStatement().setInt(1, id);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }

    /**
     * returns a list of all contacts
     * @return list of contact names
     * @throws SQLException if there is a sql error
     */
    public static ObservableList<String> getContacts() throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT Contact_Name\n" +
                "FROM contacts",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        ObservableList<String> contacts = FXCollections.observableArrayList();
        while (results.next()){
            contacts.add(results.getString("Contact_Name"));
        }
        temp.closeConnection();
        return contacts;
    }

    /**
     * returns a contacts id
     * @param contact contact
     * @return contact id
     * @throws SQLException if there is a sql error
     */
    public static int getContactId(String contact) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT Contact_ID\n" +
                "FROM contacts\n" + "WHERE Contact_name = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, contact);
        ResultSet results = temp.getPreparedStatement().executeQuery();
        int contactId = 0;
        while (results.next()){
            contactId = results.getInt("Contact_ID");
        }
        temp.closeConnection();
        return contactId;
    }

    /**
     * add an appointment to the database
     * @param title title
     * @param description description
     * @param loc location
     * @param contact contact
     * @param type type
     * @param start start date and time
     * @param end end date and time
     * @param custId customer id
     * @param userId user id
     * @throws SQLException if there is a sql error
     */
    public static void addAppt(String title, String description, String loc, int contact, String type, ZonedDateTime start, ZonedDateTime end, int custId, int userId) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?)",temp.getConnection());
        temp.getPreparedStatement().setString(1, title);
        temp.getPreparedStatement().setString(2, description);
        temp.getPreparedStatement().setString(3, loc);
        temp.getPreparedStatement().setString(4, type);
        temp.getPreparedStatement().setTimestamp(5, Timestamp.from(start.withZoneSameInstant(ZoneOffset.UTC).toInstant()));
        temp.getPreparedStatement().setTimestamp(6, Timestamp.from(end.withZoneSameInstant(ZoneOffset.UTC).toInstant()));
        temp.getPreparedStatement().setInt(7, custId);
        temp.getPreparedStatement().setInt(8, userId);
        temp.getPreparedStatement().setInt(9, contact);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }

    /**
     * updates an appointment in the database
     * @param id id
     * @param title title
     * @param description description
     * @param loc location
     * @param contact contact
     * @param type type
     * @param start start date and time
     * @param end end date and time
     * @param custId customer id
     * @param userId user id
     * @throws SQLException if there is a sql error
     */
    public static void updateAppt(int id, String title, String description, String loc, int contact, String type, ZonedDateTime start, ZonedDateTime end, int custId, int userId) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("Update appointments\n" +
                "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End= ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?\n" + "WHERE Appointment_ID = ?",temp.getConnection());
        temp.getPreparedStatement().setString(1, title);
        temp.getPreparedStatement().setString(2, description);
        temp.getPreparedStatement().setString(3, loc);
        temp.getPreparedStatement().setString(4, type);
        temp.getPreparedStatement().setTimestamp(5, Timestamp.from(start.withZoneSameInstant(ZoneOffset.UTC).toInstant()));
        temp.getPreparedStatement().setTimestamp(6, Timestamp.from(end.withZoneSameInstant(ZoneOffset.UTC).toInstant()));
        temp.getPreparedStatement().setInt(7, custId);
        temp.getPreparedStatement().setInt(8, userId);
        temp.getPreparedStatement().setInt(9, contact);
        temp.getPreparedStatement().setInt(10, id);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }

    /**
     * deletes an appointment in the database
     * @param id appointment id
     * @throws SQLException if there is a sql error
     */
    public static void deleteApptId(int id) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("DELETE FROM appointments\n" +
                "WHERE Appointment_ID = ?",temp.getConnection());
        temp.getPreparedStatement().setInt(1, id);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }

    /**
     * deletes all the appointments associated with a customer
     * @param id customer id
     * @throws SQLException if there is a sql error
     */
    public static void deleteApptCustomer(int id) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("DELETE FROM appointments\n" +
                "WHERE Customer_ID = ?",temp.getConnection());
        temp.getPreparedStatement().setInt(1, id);
        temp.getPreparedStatement().executeUpdate();
        temp.closeConnection();
    }

    /**
     * builds the overview list
     * @throws SQLException if there is a sql error
     */
    public static void overview() throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT Month(Start) AS month, Type, COUNT(Appointment_ID) AS Num\n" +
                "FROM appointments\n" + "GROUP BY month, Type\n" + "ORDER BY month",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()) {
            Overview tempOver = new Overview(Month.of(results.getInt("month")).name(), results.getString("Type"), results.getInt("Num"));
            System.out.println(tempOver.getMonth() + " " + tempOver.getType() + " " + tempOver.getCount());
            OverviewList.addOverview(tempOver);
            ObservableList<Overview> tempOvers1  = OverviewList.getAllOverviews();
            tempOvers1.forEach(overview -> System.out.println(overview.getMonth() + " " + overview.getType() + " " + overview.getCount()));
        }
        ObservableList<Overview> tempOvers = OverviewList.getAllOverviews();
        tempOvers.forEach(overview -> System.out.println(overview.getMonth() + " " + overview.getType() + " " + overview.getCount()));
        temp.closeConnection();
    }

}


