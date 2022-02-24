package com.example.software2project.controller;

import com.example.software2project.JDBC;
import com.example.software2project.Main;
import com.example.software2project.model.Appointment;
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
 * Class Modify Appointment Controller
 */

/**
 * @author Jameson Shirley
 */
public class ModifyApptController {
    Stage stage;
    Parent scene;
    /**
     * combobox for contact name
     */
    @FXML
    private ComboBox<String> contact;
    /**
     * textfield for customer ID
     */
    @FXML
    private TextField custID;
    /**
     * textarea for description
     */
    @FXML
    private TextArea description;
    /**
     * textfield for end date
     */
    @FXML
    private TextField endDate;
    /**
     * textfield for end time
     */
    @FXML
    private TextField endTime;
    /**
     * textfield for id
     */
    @FXML
    private TextField id;
    /**
     * textfield for location
     */
    @FXML
    private TextField loc;
    /**
     * textfield for start date
     */
    @FXML
    private TextField startDate;
    /**
     * textfield for start time
     */
    @FXML
    private TextField startTime;
    /**
     * text field for title
     */
    @FXML
    private TextField title;
    /**
     * textfield for type
     */
    @FXML
    private TextField type;
    /**
     * textfield for user ID
     */
    @FXML
    private TextField userID;

    /**
     * cancels the modify appointment dialogue
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * checks if the time is in business hours
     * @param startTime starting time
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
     * makes sure there is no overlap between appointments
     * @param start2 start time
     * @param end2 end time
     * @param id appointment id
     * @return boolean
     * @throws SQLException if there is a sql error
     */
    public static boolean noOverlap(ZonedDateTime start2, ZonedDateTime end2, int id) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        JDBC temp = new JDBC();
        temp.makeConnection();
        temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                "FROM appointments a\n" + "INNER JOIN contacts c ON c.Contact_ID = a.Contact_ID", temp.getConnection());
        ResultSet results = temp.getPreparedStatement().executeQuery();
        while (results.next()) {
            if(results.getInt("Appointment_ID") != id) {
                ZonedDateTime start = ZonedDateTime.ofInstant(results.getTimestamp("Start").toInstant(), ZoneId.of("US/Eastern"));
                ZonedDateTime end = ZonedDateTime.ofInstant(results.getTimestamp("End").toInstant(), ZoneId.of("US/Eastern"));
                if (Date.from(start.toInstant()).before(Date.from(end2.toInstant())) && Date.from(start2.toInstant()).before(Date.from(end.toInstant()))) {
                    temp.closeConnection();
                    return true;
                }
            }
        }
        temp.closeConnection();
        return false;
    }

    /**
     *
     * @param event event
     * @throws IOException if the page fails to load
     * Saves the modified appointment
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
                if (!noOverlap(startEastern, endEastern, Integer.parseInt(id.getText()))) {
                    Appointment appt = new Appointment(1, title.getText(), description.getText(), loc.getText(), "temp", type.getText(), "startDateTime", "endDateTime", Integer.parseInt(custID.getText()), Integer.parseInt(userID.getText()));
                    query.updateAppt(Integer.parseInt(id.getText()), appt.getTitle(), appt.getDescription(), appt.getLoc(), contactId, appt.getType(), startDateTime, endDateTime, appt.getCustId(), appt.getUserId());
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

    /**
     * send an appointment to the page
     * @param appt appointment
     * @throws SQLException if there is a sql error
     */
    public void sendAppt(Appointment appt) throws SQLException {
        String[] arrEnd = appt.getEnd().split(" ", 3);
        String[] arrStart = appt.getStart().split(" ", 3);
        Query query = new Query();
        contact.setItems(query.getContacts());
        contact.getSelectionModel().select(appt.getContact());
        custID.setText(String.valueOf(appt.getCustId()));
        description.setText(String.valueOf(appt.getDescription()));
        id.setText((String.valueOf(appt.getId())));
        endDate.setText(String.valueOf(arrEnd[0]));
        endTime.setText(String.valueOf(arrEnd[2]));
        startDate.setText(String.valueOf(arrStart[0]));
        startTime.setText(String.valueOf(arrStart[2]));
        loc.setText(String.valueOf(appt.getLoc()));
        title.setText(String.valueOf(appt.getTitle()));
        type.setText(String.valueOf(appt.getType()));
        userID.setText(String.valueOf(appt.getUserId()));
    }

}