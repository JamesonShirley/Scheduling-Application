package com.example.software2project.controller;

import com.example.software2project.JDBC;
import com.example.software2project.Main;
import com.example.software2project.model.Appointment;
import com.example.software2project.model.ApptList;
import com.example.software2project.model.Customer;
import com.example.software2project.model.Query;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Class Add Appointment Controller
 */

/**
 * @author Jameson Shirley
 */
public class AddApptController {
    /***
     * Function that initializes the page and sets contact drop down items
     * @throws SQLException if there is a sql error
     */
    public void initialize() throws SQLException {
        Query query = new Query();
        contact.setItems(query.getContacts());
    }
    Stage stage;
    Parent scene;
    /***
     * combobox for contacts
     */
    @FXML
    private ComboBox<String> contact;
    /***
     * text field for Customer ID
     */
    @FXML
    private TextField custID;
    /***
     * text area for description
     */
    @FXML
    private TextArea description;
    /***
     * textfield for end date
     */
    @FXML
    private TextField endDate;
    /***
     * textfield for end time
     */
    @FXML
    private TextField endTime;
    /***
     * textfield for location
     */
    @FXML
    private TextField loc;
    /***
     * textfield for start date
     */
    @FXML
    private TextField startDate;
    /***
     * text field for start time
     */
    @FXML
    private TextField startTime;
    /***
     * textfield for title
     */
    @FXML
    private TextField title;
    /***
     * textfield for type
     */
    @FXML
    private TextField type;
    /***
     * textfield for user ID
     */
    @FXML
    private TextField userID;

    /***
     * button that cancels adding appointment
     * @param event event
     * @throws IOException if there is an issue loading the new page
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /***
     * checks if a given time falls in between two other times
     * @param startTime start time
     * @param endTime end time
     * @param checkTime time to check
     * @return boolean
     */
    private static boolean checkTime(String startTime, String endTime, String checkTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
        LocalTime startLocalTime = LocalTime.parse(startTime, formatter);
        LocalTime endLocalTime = LocalTime.parse(endTime, formatter);
        LocalTime checkLocalTime = LocalTime.parse(checkTime, formatter);

        boolean isInBetween = false;
        if (endLocalTime.isAfter(startLocalTime)) {
            if (startLocalTime.isBefore(checkLocalTime) && endLocalTime.isAfter(checkLocalTime)) {
                isInBetween = true;
            }
        } else if (checkLocalTime.isAfter(startLocalTime) || checkLocalTime.isBefore(endLocalTime)) {
            isInBetween = true;
        }

        return isInBetween;
    }

    /**
     * Checks if a time will overlap another appointment
     * @param start2 start time
     * @param end2 end time
     * @return  boolean
     * @throws SQLException if there is a sql error
     */
    public static boolean noOverlap(ZonedDateTime start2, ZonedDateTime end2) throws SQLException {
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID",temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()){
            ZonedDateTime start = ZonedDateTime.ofInstant(results.getTimestamp("Start").toInstant(), ZoneId.of("US/Eastern"));
            ZonedDateTime end = ZonedDateTime.ofInstant(results.getTimestamp("End").toInstant(), ZoneId.of("US/Eastern"));
            if (Date.from(start.toInstant()).before(Date.from(end2.toInstant())) && Date.from(start2.toInstant()).before(Date.from(end.toInstant()))){
                temp.closeConnection();
                return true;
            }
            }
        temp.closeConnection();
        return false;
    }

    /**
     * saves if values are legitimate and displays messages
     * @param event event
     * @throws IOException if the page doesnt load
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException {
        try {
            Query query = new Query();
            int contactId = query.getContactId(contact.getSelectionModel().getSelectedItem());
            String[] dateArr = startDate.getText().split("/", 3);
            String[] timeArr = startTime.getText().split(":", 2);
            ZonedDateTime startDateTime = ZonedDateTime.of(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]), 00, 1234, ZoneId.of(ZoneId.systemDefault().getId()));
            dateArr = endDate.getText().split("/", 3);
            timeArr = endTime.getText().split(":", 2);
            ZonedDateTime endDateTime = ZonedDateTime.of(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]), 00, 1234, ZoneId.of(ZoneId.systemDefault().getId()));
            ZonedDateTime startEastern = startDateTime.withZoneSameInstant(ZoneId.of("US/Eastern"));
            ZonedDateTime endEastern = endDateTime.withZoneSameInstant(ZoneId.of("US/Eastern"));
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            String startTimeEastern = startEastern.format(timeFormat);
            String endTimeEastern = endEastern.format(timeFormat);
            if (checkTime("08:00", "22:00", startTimeEastern) && checkTime("08:00", "22:00", endTimeEastern)) {
                if (!noOverlap(startEastern, endEastern)) {
                    Appointment appt = new Appointment(1, title.getText(), description.getText(), loc.getText(), "temp", type.getText(), "startDateTime", "endDateTime", Integer.parseInt(custID.getText()), Integer.parseInt(userID.getText()));
                    query.addAppt(appt.getTitle(), appt.getDescription(), appt.getLoc(), contactId, appt.getType(), startDateTime, endDateTime, appt.getCustId(), appt.getUserId());
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Overlapping appointments");
                    alert.setContentText("Please enter times that do not overlap with another appointment.");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Out of Business Hours");
                alert.setContentText("Please enter times within valid business hours.");
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please enter valid inputs");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please enter valid inputs");
            alert.showAndWait();
        }
    }

    }