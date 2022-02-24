package com.example.software2project.controller;

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
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddApptController {
    public void initialize() throws SQLException {
        Query query = new Query();
        contact.setItems(query.getContacts());
    }
    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<String> contact;

    @FXML
    private TextField custID;

    @FXML
    private TextArea description;

    @FXML
    private TextField endDate;

    @FXML
    private TextField endTime;

    @FXML
    private TextField loc;

    @FXML
    private TextField startDate;

    @FXML
    private TextField startTime;

    @FXML
    private TextField title;

    @FXML
    private TextField type;

    @FXML
    private TextField userID;

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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
                Appointment appt = new Appointment(1, title.getText(), description.getText(), loc.getText(), "temp", type.getText(), "startDateTime", "endDateTime", Integer.parseInt(custID.getText()), Integer.parseInt(userID.getText()));
                query.addAppt(appt.getTitle(), appt.getDescription(), appt.getLoc(), contactId, appt.getType(), startDateTime, endDateTime, appt.getCustId(), appt.getUserId());
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
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