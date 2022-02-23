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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ModifyApptController {
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
    private TextField id;

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
            Appointment appt = new Appointment(1, title.getText(), description.getText(), loc.getText(), "temp", type.getText(), "startDateTime", "endDateTime", Integer.parseInt(custID.getText()), Integer.parseInt(userID.getText()));
            query.updateAppt(Integer.parseInt(id.getText()), appt.getTitle(), appt.getDescription(), appt.getLoc(), contactId, appt.getType(), startDateTime, endDateTime, appt.getCustId(), appt.getUserId());
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NumberFormatException e){
            System.out.println("Error message here Format");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void sendAppt(Appointment appt) throws SQLException {
        String[] arrEnd = appt.getEnd().split(" ", 3);
        String[] arrStart = appt.getEnd().split(" ", 3);
        Query query = new Query();
        contact.setItems(query.getContacts());
        contact.getSelectionModel().select(appt.getContact());
        custID.setText(String.valueOf(appt.getCustId()));
        description.setText(String.valueOf(appt.getDescription()));
        id.setText((String.valueOf(appt.getId())));
        endDate.setText(String.valueOf(arrEnd[0]));
        endTime.setText(String.valueOf(arrEnd[2]));
        startDate.setText(String.valueOf(arrStart[0]));
        startTime.setText(String.valueOf(arrEnd[2]));
        loc.setText(String.valueOf(appt.getLoc()));
        title.setText(String.valueOf(appt.getTitle()));
        type.setText(String.valueOf(appt.getType()));
        userID.setText(String.valueOf(appt.getUserId()));
    }

}